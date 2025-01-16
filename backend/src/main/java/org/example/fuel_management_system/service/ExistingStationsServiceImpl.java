package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExistingStationsServiceImpl {
@Autowired
    private ExistingStationsRepository existingStationsRepository;

    public boolean isLicenseNumberValid(String licenseNumber) {
        // Query the database to check if the license number exists
        return existingStationsRepository.existsByLicense_number(licenseNumber);
    }



}
