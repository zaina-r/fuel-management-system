package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.Station;
import org.springframework.beans.factory.annotation.Autowired;

public class StationService {

    @Autowired
    private FuelStationRepository fuelStationRepository;

    public boolean isStationIdValid(String stationId){
        return !fuelStationRepository.existsByStationId(Integer.parseInt(stationId));
    }

    public Station saveStation(Station station){
        return fuelStationRepository.save(station);
    }

}
