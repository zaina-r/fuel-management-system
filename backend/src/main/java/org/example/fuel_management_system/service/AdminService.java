package org.example.fuel_management_system.service;


import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.StationWithRegistrationStatus;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelAllocationRepository;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.ExistingStations;
import org.example.fuel_management_system.model.Fuel;

import org.example.fuel_management_system.model.FuelAllocation;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final FuelStationRepository fuelStationRepository;
    private final ExistingStationsRepository existingStationsRepository;
    private final FuelRepository fuelRepository;
    private final FuelAllocationRepository fuelAllocationRepository;

    public AdminService(FuelStationRepository fuelStationRepository, ExistingStationsRepository existingStationsRepository, FuelRepository fuelRepository, FuelAllocationRepository fuelAllocationRepository){
        this.fuelStationRepository = fuelStationRepository;
        this.existingStationsRepository = existingStationsRepository;
        this.fuelRepository = fuelRepository;
        this.fuelAllocationRepository = fuelAllocationRepository;
    }

    public List<StationWithRegistrationStatus> getStationWithStatus(){

        List<ExistingStations> allStations = existingStationsRepository.findAll();
        List<StationWithRegistrationStatus> stationList = new ArrayList<>();

        for (ExistingStations station : allStations) {
            boolean isRegistered = fuelStationRepository.existsByStationId(station.getDealerId());
            String status = isRegistered ? "Registered" : "Not Registered";

            StationWithRegistrationStatus stationWithStatus = new StationWithRegistrationStatus(
                    station.getDealerId(),
                    station.getDealer_name(),
                    status
            );
            stationList.add(stationWithStatus);
        }

        return stationList;
    }

    public Response updateWeeklyFuelAllocation(FuelAllocation fuelAllocation) {
        Response response = new Response();
        try {
            Fuel fuel = fuelRepository.findByStationId(fuelAllocation.getStation().getId());
            if (fuel != null) {

                fuel.setWeeklyDieselAllocation(fuelAllocation.getWeeklyDieselAmount());
                fuel.setWeeklyPetrolAllocation(fuelAllocation.getWeeklyPetrolAmount());
                fuelRepository.save(fuel);


                response.setMessage("Weekly fuel allocation updated successfully.");
                response.setStatusCode(200);
            } else {

                response.setMessage("Fuel entry not found for station ID: " + fuelAllocation.getStation().getStationId());
                response.setStatusCode(404);
            }
        } catch (Exception e) {

            response.setMessage("An error occurred while updating fuel allocation: " + e.getMessage());
            response.setStatusCode(500);
        }
        return response;
    }


}
