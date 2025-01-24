package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.StationWithRegistrationStatus;
import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.ExistingStations;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdminService {

    private final FuelStationRepository fuelStationRepository;
    private final ExistingStationsRepository existingStationsRepository;

    public AdminService(FuelStationRepository fuelStationRepository, ExistingStationsRepository existingStationsRepository){
        this.fuelStationRepository = fuelStationRepository;
        this.existingStationsRepository = existingStationsRepository;
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

}
