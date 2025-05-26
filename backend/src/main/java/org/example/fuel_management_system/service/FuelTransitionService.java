package org.example.fuel_management_system.service;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.FuelTransition;
import org.springframework.stereotype.Service;


@Service
public interface FuelTransitionService {

 Response getAllFuelTransitions(int userId);

  Response getAllFuelTransitionsByStationId(String stationId);

 Response addTransaction(FuelTransition fuelTransition,int userId,int vehicleId);

 Response getAllTransaction();
}
