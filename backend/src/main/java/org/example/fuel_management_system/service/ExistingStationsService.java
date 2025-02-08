package org.example.fuel_management_system.service;


import org.example.fuel_management_system.model.ExistingStations;
import org.springframework.stereotype.Service;

@Service
public interface ExistingStationsService {

boolean isLicenseNumberValid(String licenseNumber) ;
 ExistingStations addStation(ExistingStations existingStations) ;

}