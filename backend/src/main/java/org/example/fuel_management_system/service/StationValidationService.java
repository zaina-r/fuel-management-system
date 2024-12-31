package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.ExistingStationsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StationValidationService {

    @Autowired
    private ExistingStationsRepository existingStationsRepository;

    public boolean isStationIdValid (int dealer_id){
        return existingStationsRepository.existsByDealerId(dealer_id);
    }
}
