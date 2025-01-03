package org.example.fuel_management_system.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.*;
import org.example.fuel_management_system.utilities.VerificationCodeGenerator;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
public class StationService {


    private final FuelStationRepository fuelStationRepository;
    private final FuelRepository fuelRepository;
     private final MailService mailService;
     private final AuthenticateService authenticateService;
     private final JwtService jwtService;
    private final ExistingStationsRepository existingStationsRepository;
    private final VerificationCodeGenerator verificationCodeGenerator;


    public StationService(FuelStationRepository fuelStationRepository, FuelRepository fuelRepository, MailService mailService, JwtService jwtService, VerificationCodeGenerator verificationCodeGenerator1, AuthenticateService authenticateService, ExistingStationsRepository existingStationsRepository) {
        this.fuelStationRepository = fuelStationRepository;
        this.fuelRepository = fuelRepository;
        this.mailService = mailService;
this.authenticateService=authenticateService;
        this.verificationCodeGenerator = verificationCodeGenerator1;
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

    @Transactional
    public Station saveStation(Station station) {

        if (station.getStationId() != 0) {
            Station existingStation = new Station();

            existingStation.setStationAddress(station.getStationAddress());
            existingStation.setDealerName(station.getDealerName());
            existingStation.setRegistrationDate(LocalDate.now());
           // existingStation.setStationId(station.getStationId());

            String verficationCode=verificationCodeGenerator.generateVerificationCode();
            existingStation.setLoginCode(verficationCode);

            MailStructure mailStructure = new MailStructure();
            mailStructure.setSubject("Station Verification Code");
            mailStructure.setMessage("Your verification code is: " + verficationCode);
            System.out.println("jenushan");
            String username = getUsernameFromToken();
            System.out.println("Email is: "+username);
            mailService.sendMail(username, mailStructure);







            return fuelStationRepository.save(existingStation);
        }

        station.setRegistrationDate(LocalDate.now());
        return fuelStationRepository.save(station);
    }


    public List<Station> getAllStations() {
        return fuelStationRepository.findAll();
    }

    public Station findStationById(int id) {
        return fuelStationRepository.findById(id).orElseThrow(()->new EntityNotFoundException("station with id "+id+"not found"));
    }

    public List<Fuel> getFuelsByStationId(int stationId) {
        return fuelRepository.findByStation_StationId(stationId);
    }

    public boolean isStationIdValid (int dealer_id){

        Optional<ExistingStations>existingStations= existingStationsRepository.findById(dealer_id);
        if(existingStations.isPresent()){
            return true;
        }
        return false;

    }


}
