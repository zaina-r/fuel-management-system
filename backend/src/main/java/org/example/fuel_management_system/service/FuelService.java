package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.StationDto;
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
        Response response=stationService.findStationById(stationId);
        StationDto stationDto= response.getStationDto();
        Station fuelStation=new Station(stationDto.getId(),stationDto.getStationId(),stationDto.getStationAddress(),
                stationDto.getDealerName(),stationDto.getLicenseNumber(),stationDto.getRegistrationDate(),stationDto.getLoginCode(),stationDto.getFuel());
        Fuel fuel=new Fuel();
        fuel.setStation(fuelStation);
        fuel.setAvailablePetrolQuantity(petrolCapacity);
        fuel.setAvailableDiselQuantity(dieselCapacity);
        return fuelRepository.save(fuel);
    }

    public List<Fuel> getFuelQuantities() {
        return fuelRepository.findAll();
    }

}
