package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {
    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private StationService stationService;
    @Autowired
    private FuelStationRepository fuelStationRepository;

    public Fuel addFuel(int stationId, float petrolCapacity, float dieselCapacity) {
        Station fuelStation=stationService.findStationById(stationId);
        Fuel fuel=new Fuel();
        fuel.setStation(fuelStation);
        fuel.setAvailablePetrolQuantiy(petrolCapacity);
        fuel.setAvailableDiselQuantiy(dieselCapacity);
        return fuelRepository.save(fuel);
    }

}
