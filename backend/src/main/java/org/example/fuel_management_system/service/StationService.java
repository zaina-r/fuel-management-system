package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class StationService {

    @Autowired
    final FuelStationRepository fuelStationRepository;

    public StationService(FuelStationRepository fuelStationRepository) {
        this.fuelStationRepository = fuelStationRepository;
    }

    public boolean doesStationIdExist(int stationId){
        return !fuelStationRepository.existsByStationId(stationId);
    }

    public void saveStation(Station station){
        fuelStationRepository.save(station);
    }

}
