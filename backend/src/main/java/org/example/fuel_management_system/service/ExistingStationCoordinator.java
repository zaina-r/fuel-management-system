package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.example.fuel_management_system.model.ExistingStations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExistingStationCoordinator implements ExistingStationsService{
    @Autowired
    private ExistingStationsRepository existingStationsRepository;

    public boolean isLicenseNumberValid(String licenseNumber) {
        return existingStationsRepository.existsByLicenseNumber(licenseNumber);
    }

    public ExistingStations addStation(ExistingStations existingStations) {
        return existingStationsRepository.save(existingStations);
    }
}
