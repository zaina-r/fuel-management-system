package org.example.fuel_management_system.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.model.*;
import org.example.fuel_management_system.utilities.VerificationCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
public class StationService {


    private final FuelStationRepository fuelStationRepository;
    private final FuelRepository fuelRepository;
     private final MailService mailService;
     private final AuthenticateService authenticateService;
     private final JwtService jwtService;
    private final ExistingStationsRepository existingStationsRepository;
    private final VerificationCodeService verificationCodeService;
    @Autowired
    private UserAccountRepository userAccountRepository;


    public StationService(FuelStationRepository fuelStationRepository, FuelRepository fuelRepository, MailService mailService, JwtService jwtService, VerificationCodeService verificationCodeGenerator1, AuthenticateService authenticateService, ExistingStationsRepository existingStationsRepository) {
        this.fuelStationRepository = fuelStationRepository;
        this.fuelRepository = fuelRepository;
        this.mailService = mailService;
this.authenticateService=authenticateService;
        this.verificationCodeService = verificationCodeGenerator1;
        this.jwtService=jwtService;
        this.existingStationsRepository = existingStationsRepository;
    }

    public boolean doesStationIdExist(String stationName){

        Optional<Station> station= fuelStationRepository.findByDealerName(stationName);

        if(station.isPresent()){
            return true;
        }
        else {
            return false;

        }
    }

    public Station addFuelToStation(int stationId, List<Fuel> fuels) {
        Station station = fuelStationRepository.findById(stationId)
                .orElseThrow(() -> new RuntimeException("Station not found"));

        for (Fuel fuel : fuels) {
            fuel.setStation(station);
        }

        station.getFuel().addAll(fuels);

        return fuelStationRepository.save(station);
    }

    public String getUsernameFromToken() {
        String token = jwtService.getTokenFromHeader();
        if (token != null) {
            return jwtService.extractUsername(token);
        }
        throw new IllegalStateException("Token not present or invalid.");
    }

    public Station saveStation(Station station) throws Exception {
        try {
            // Check if the station already exists by license number
            Optional<Station> existingStationOptional = existingStationsRepository.findByLicenseNumber(station.getLicenseNumber());

            if (existingStationOptional.isPresent()) {
                // If the station already exists, update the station details
                Station existingStation = existingStationOptional.get();

                existingStation.setStationAddress(station.getStationAddress());
                existingStation.setDealerName(station.getDealerName());
                existingStation.setRegistrationDate(LocalDate.now());

                // Generate and send OTP
                String otp = GenerateOtp.generateOtp();
                UserAccount user = userAccountRepository.findByLicenseNumber(existingStation.getLicenseNumber());

                if (user != null) {
                    VerificationCode verificationCode = verificationCodeService.generateOtpForStation(existingStation, otp, user.getUsername());
                    existingStation.setLoginCode(verificationCode.getOtp());
                    existingStation.setRegistrationDate(LocalDate.now());
                } else {
                    throw new Exception("User associated with license number not found.");
                }

                // Save the updated station
                return fuelStationRepository.save(existingStation);

            } else {
                // If the station does not exist, create a new station
                Station newStation = new Station();
                newStation.setLicenseNumber(station.getLicenseNumber());
                newStation.setStationAddress(station.getStationAddress());
                newStation.setDealerName(station.getDealerName());
                newStation.setRegistrationDate(LocalDate.now());

                // Save the new station
                Station savedStation = fuelStationRepository.save(newStation);

                // Generate and send OTP for the newly registered station
                String otp = GenerateOtp.generateOtp();
                UserAccount user = userAccountRepository.findByLicenseNumber(savedStation.getLicenseNumber());

                if (user != null) {
                    VerificationCode verificationCode = verificationCodeService.generateOtpForStation(savedStation, otp, user.getUsername());
                    savedStation.setLoginCode(verificationCode.getOtp());
                    fuelStationRepository.save(savedStation);

                    // Simulate sending OTP to the user's email
                    System.out.println("OTP sent to email: " + user.getUsername() + " OTP: " + verificationCode.getOtp());
                } else {
                    throw new Exception("User associated with license number not found.");
                }

                return savedStation;
            }
        } catch (Exception e) {
            throw new Exception("Error while registering the station: " + e.getMessage());
        }
    }







    public List<Station> getAllStations() {
        return fuelStationRepository.findAll();
    }

    public Station findStationById(int id) {
        return fuelStationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("station with id "+id+"not found"));
    }

    public List<Fuel> getFuelsByStationId(int stationId) {
        return fuelRepository.findByStationId(stationId);
    }

    public boolean isStationIdValid (int dealer_id){

        Optional<ExistingStations>existingStations= existingStationsRepository.findById(dealer_id);
        if(existingStations.isPresent()){
            return true;
        }
        return false;

    }


}
