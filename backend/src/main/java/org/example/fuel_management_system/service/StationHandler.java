package org.example.fuel_management_system.service;

import jakarta.persistence.EntityNotFoundException;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.OtpGenerator.GenerateOtp;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.service.MailService;
import org.example.fuel_management_system.Request.StationRequest;
import org.example.fuel_management_system.model.ExistingStations;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.model.VerificationCode;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class StationHandler implements StationService{
    private final FuelStationRepository fuelStationRepository;
    private final FuelRepository fuelRepository;
        private final MailService mailService;
    private final AuthenticateService authenticateService;
    private final JwtService jwtService;
    private final ExistingStationsRepository existingStationsRepository;
    private final VerificationCodeService verificationCodeService;
    private final UserAccountRepository userAccountRepository;

    public StationHandler(FuelStationRepository fuelStationRepository, FuelRepository fuelRepository, MailService mailService, AuthenticateService authenticateService, JwtService jwtService, ExistingStationsRepository existingStationsRepository, VerificationCodeService verificationCodeService, UserAccountRepository userAccountRepository) {
        this.fuelStationRepository = fuelStationRepository;
        this.fuelRepository = fuelRepository;
        this.mailService = mailService;
        this.authenticateService = authenticateService;
        this.jwtService = jwtService;
        this.existingStationsRepository = existingStationsRepository;
        this.verificationCodeService = verificationCodeService;
        this.userAccountRepository = userAccountRepository;
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
            response.setMessage("  stations are not get retrieved successfully ");

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
            response.setMessage("The station is not currently retrieving  ");

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

    public Response deleteStationById(int id){
        Response response=new Response();
        Optional<Station> station=fuelStationRepository.findById(id);
        if(station.isPresent()){
            fuelRepository.deleteById(id);
            response.setStatusCode(200);
            response.setMessage("Station deleted successfully!");
            return response;
        }
        response.setStatusCode(400);
        response.setMessage("Station cannot identified !");
        return response;
    }

    public Response saveOrUpdateStation(Station station) {
        Response response = new Response();
        System.out.println(station.getDealerName());
        System.out.println(station.getLicenseNumber());
        System.out.println(station.getId());
        try {
            Optional<ExistingStations> existingStationOptional = existingStationsRepository.findByLicenseNumber(station.getLicenseNumber());
            boolean stationOptional=fuelStationRepository.existsByStationId(station.getStationId());

            if (existingStationOptional.get().getLicense_number().equals(station.getLicenseNumber()) &&
                    existingStationOptional.get().getDealerId().equals(station.getStationId())
                    && !stationOptional
            ) {

                UserAccount user = userAccountRepository.findByLicenseNumber(station.getLicenseNumber());



                if (user != null  ) {
                    Station existingStation=fuelStationRepository.save(station);
                    String otp = GenerateOtp.generateOtp();
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
            response.setMessage("The station is not correctly registered " );
        }

        return response;
    }

    public Response findByLoginCode(String loginCode) {
        Optional<Station> station=fuelStationRepository.findByLoginCode(loginCode);
        Response response=new Response();
        if(station.isPresent()){
            response.setStatusCode(200);
            response.setMessage("Station found successfully");
            response.setStationDto(MapUtils.mapStationEntityToStationDTO(station.get()));
        }else{
            response.setStatusCode(404);
            response.setMessage("Station with login code "+loginCode+"not found");
        }
        return response;
    }

    public Response updateStation(StationRequest stationRequest, int stationId){

        Response response=new Response();
        try {


            Station station = fuelStationRepository.findById(stationId)
                    .orElseThrow(() -> new EntityNotFoundException("Station with id " + stationId + " not found"));

            if (station!=null) {
                station.setStationAddress(stationRequest.getStationAddress());
                station.setDealerName(stationRequest.getDealerName());
                fuelStationRepository.save(station);
            }
            response.setStatusCode(200);
            response.setMessage("Station retrieved successfully");
            response.setStationDto(MapUtils.mapStationEntityToStationDTO(station));
        }   catch (EntityNotFoundException e) {
            response.setStatusCode(404);
            response.setMessage(e.getMessage());

        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("Not station retrieved");

        }
        return response;



    }

}
