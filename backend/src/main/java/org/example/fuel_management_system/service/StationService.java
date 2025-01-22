package org.example.fuel_management_system.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.model.*;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
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



    public Response getFuelsByStationId(int stationId) {
        Response response = new Response();
        try {
            List<Fuel> fuels = fuelRepository.findByStationId(stationId);
            if (fuels.isEmpty()) {
                response.setStatusCode(404);
                response.setMessage("No fuels found for the given station ID.");

            }
            else{
                response.setStatusCode(200);
                response.setMessage("Fuels retrieved successfully.");
                response.setFuelDtoList(MapUtils.mapFuelListEntityToFuelListDTO(fuels));

            }



        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving fuels: " + e.getMessage());

        }
        return response;
    }

    public Response getAllStations() {
        Response response = new Response();
        try {
            List<Station> stations = fuelStationRepository.findAll();
            response.setStatusCode(200);
            response.setMessage("Stations retrieved successfully");
            response.setStationDtosList(MapUtils.mapUserListEntityToStationListDTO(stations));

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving stations: " + e.getMessage());

        }


        return response;
    }

    public Response findStationById(int id) {
        Response response = new Response();

        try {
            Station station = fuelStationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Station with id " + id + " not found"));

            response.setStatusCode(200);
            response.setMessage("Station retrieved successfully");
            response.setStationDto(MapUtils.mapStationEntityToStationDTO(station));

        } catch (EntityNotFoundException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while retrieving the station: " + e.getMessage());

        }
        return response;
    }

    public Response addFuelToStation(int stationId, List<Fuel> fuels) {
        Response response = new Response();

        try {
            Station station = fuelStationRepository.findById(stationId)
                    .orElseThrow(() -> new RuntimeException("Station not found"));

            for (Fuel fuel : fuels) {
                fuel.setStation(station);
            }

            station.getFuel().addAll(fuels);

            Station updatedStation = fuelStationRepository.save(station);

            response.setStatusCode(200);
            response.setMessage("Fuels added to station successfully");
            response.setStationDto(MapUtils.mapStationEntityToStationDTO(updatedStation));


        } catch (RuntimeException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Error occurred while adding fuels to station: " + e.getMessage());

        }
        return response;
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

    public boolean isStationIdValid (int dealer_id){

        Optional<ExistingStations>existingStations= existingStationsRepository.findById(dealer_id);
        if(existingStations.isPresent()){
            return true;
        }
        return false;

    }


        public Response saveOrUpdateStation(Station station) {
            Response response = new Response();

            try {
                Optional<ExistingStations> existingStationOptional = existingStationsRepository.findByLicenseNumber(station.getLicenseNumber());
               boolean stationOptional=fuelStationRepository.existsByStationId(station.getStationId());

                if (existingStationOptional.get().getLicense_number().equals(station.getLicenseNumber()) &&
                        existingStationOptional.get().getDealerId().equals(station.getStationId())
                && !stationOptional
                ) {
                    Station existingStation=fuelStationRepository.save(station);
                    existingStation.setRegistrationDate(LocalDate.now());

                    String otp = GenerateOtp.generateOtp();
                    UserAccount user = userAccountRepository.findByLicenseNumber(existingStation.getLicenseNumber());

                    if (user != null) {
                        VerificationCode verificationCode = verificationCodeService.generateOtpForStation(existingStation, otp, user.getUsername());
                        existingStation.setLoginCode(verificationCode.getOtp());
                        existingStation.setRegistrationDate(LocalDate.now());
                        fuelStationRepository.save(existingStation);
                        response.setStatusCode(200);
                        response.setMessage("Station updated successfully");
                        response.setStationDto(MapUtils.mapStationEntityToStationDTO(existingStation));
                    } else {
                        response.setStatusCode(404);
                        response.setMessage("User associated with license number not found.");
                    }

                } else {

                    response.setStatusCode(404);
                    response.setMessage("Station can not with in the existing stations  .");

                }

            } catch (Exception e) {
                response.setStatusCode(500);
                response.setMessage("Error while registering the station: " + e.getMessage());
            }

            return response;
        }

    }















