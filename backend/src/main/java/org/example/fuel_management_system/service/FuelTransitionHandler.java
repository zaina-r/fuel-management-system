package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.FuelTransitionDto;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Repository.FuelTransitionRepository;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.FuelTransition;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.model.VehicleVerification;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FuelTransitionHandler implements FuelTransitionService {
    @Autowired
    private FuelTransitionRepository fuelTransitionRepository;
    @Autowired
    private UserAccountRepository userAccountRepository;
    @Autowired
    private VehicleVerificationRepository vehicleVerificationRepository;



    public Response getAllFuelTransitions(int userId){
        Response response=new Response();
        try{
            List<FuelTransition> fuelTransitions =fuelTransitionRepository.findByUserAccount_UserId(userId);
            if(fuelTransitions.isEmpty()){
                response.setStatusCode(400);
                response.setMessage("No fuel transitions found");
                return response;
            }
            response.setStatusCode(200);
            response.setMessage("fuel transitions found");

            response.setFuelTransitionDtoList(MapUtils.mapListFuelTransitionDtoToFuelTransition(fuelTransitions));
            return response;
        }catch (Exception e){
            response.setStatusCode(400);
            response.setMessage("No fuel transitions found");
            return response;
        }}

    public Response getAllTransaction(){
        Response response=new Response();
        try{
            List<FuelTransition>fuelTransitions=fuelTransitionRepository.findAll();
            if(fuelTransitions.isEmpty()){
                response.setStatusCode(400);
                response.setMessage("No fuel transitions found");
            }
            response.setStatusCode(200);
            response.setMessage("fuel transitions found");
            response.setFuelTransitionDtoList(MapUtils.mapListFuelTransitionDtoToFuelTransition(fuelTransitions));

        }catch (Exception e){
            response.setStatusCode(400);
            response.setMessage("No fuel transitions found");
        }
        return response;
    }


    public Response getAllFuelTransitionsByStationId(String stationId){
        Response response=new Response();
        try{
            List<FuelTransition> fuelTransitions =fuelTransitionRepository.findByStationId(stationId);
            if(fuelTransitions.size()==0){
                response.setStatusCode(400);
                response.setMessage("No fuel transitions found");
                return response;
            }
            response.setStatusCode(200);
            response.setMessage("fuel transitions found");

            response.setFuelTransitionDtoList(MapUtils.mapListFuelTransitionDtoToFuelTransition(fuelTransitions));
            return response;
        }catch (Exception e){
            response.setStatusCode(400);
            response.setMessage("No fuel transitions found");
            return response;
        }}

    public Response addTransaction(FuelTransition fuelTransition,int userId,int vehicleId) {
        Response response = new Response();
        try {
            UserAccount userAccount=userAccountRepository.findById(userId).orElseThrow(()->new RuntimeException("User not found"));
            VehicleVerification vehicleVerification=vehicleVerificationRepository.findById(vehicleId).orElseThrow(()->new RuntimeException("Vehicle not found"));

            fuelTransition.setTransitionTime(LocalDateTime.now());
            fuelTransition.setUserAccount(userAccount);
            fuelTransition.setVehicleVerification(vehicleVerification);
            FuelTransition fuelTransitionSaved = fuelTransitionRepository.save(fuelTransition);
            response.setStatusCode(200);
            response.setMessage("Transaction added successfully");
            response.setFuelTransitionDto(MapUtils.mapFuelTransitionEntityToFuelTransitionDTO(fuelTransitionSaved));
        } catch (Exception e) {
            response.setStatusCode(400);
            response.setMessage("Transaction addition failed: " + e.getMessage());
        }
        return response;
    }



}
