package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.StationDto;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.Request.FuelRequest;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface FuelService {


 Response addFuel(int stationId, float petrolCapacity, float dieselCapacity) ;

 Response getFuelQuantities() ;
Response updateFuel(int stationId, FuelRequest request) ;

 Response fuelDetails(int stationId);


}
