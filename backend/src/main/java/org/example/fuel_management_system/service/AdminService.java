package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.ExistingStations;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    private final FuelStationRepository fuelStationRepository;
    private final ExistingStationsRepository existingStationsRepository;

    public AdminService(FuelStationRepository fuelStationRepository, ExistingStationsRepository existingStationsRepository){
        this.fuelStationRepository = fuelStationRepository;
        this.existingStationsRepository = existingStationsRepository;
    }

}
