package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.FuelTransitionDto;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Repository.FuelTransitionRepository;
import org.example.fuel_management_system.model.FuelTransition;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class FuelTransitionService {

    @Autowired
    private FuelTransitionRepository fuelTransitionRepository;



    public Response getAllFuelTransitions(){
        Response response=new Response();
        try{
            List<FuelTransition> fuelTransitions =fuelTransitionRepository.findAll();
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

    public Response addTransaction(FuelTransition fuelTransition) {
        Response response = new Response();
        try {

            fuelTransition.setTransitionTime(LocalDateTime.now());


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
