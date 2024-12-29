package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.ExistingStationsRepository;

public class StationValidationService {

    private ExistingStationsRepository existingStationsRepository;

    public boolean isStationIdValid (int stationId){
        return existingStationsRepository.existsByStationId(stationId);
    }
}
