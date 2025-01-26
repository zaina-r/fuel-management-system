package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.FuelAllocation;
import org.example.fuel_management_system.DTO.StationWithRegistrationStatus;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.ExistingStations;
import org.example.fuel_management_system.model.Fuel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final FuelStationRepository fuelStationRepository;
    private final ExistingStationsRepository existingStationsRepository;
    private final FuelRepository fuelRepository;

    public AdminService(FuelStationRepository fuelStationRepository, ExistingStationsRepository existingStationsRepository, FuelRepository fuelRepository){
        this.fuelStationRepository = fuelStationRepository;
        this.existingStationsRepository = existingStationsRepository;
        this.fuelRepository = fuelRepository;
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

    public void updateWeeklyFuelAllocation (FuelAllocation fuelAllocation) {
        Fuel fuel = fuelRepository.findByStationId(fuelAllocation.getDealerId())
        if (fuel != null) {
            fuel.setWeeklyDieselAllocation(fuelAllocation.getWeeklyDieselAmount());
            fuel.setWeeklyPetrolAllocation(fuelAllocation.getWeeklyPetrolAmount());
            fuelRepository.save(fuel);
        } else {
            throw new RuntimeException("Fuel entry not found for dealer ID: " + fuelAllocation.getDealerId());
        }
    }

}
