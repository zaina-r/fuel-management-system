package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.Vehicle;

public interface VehicleService {

    Response addVehicle(Vehicle vehicle);
    Response getVehicle(int vehicleId);
    Response updateVehicle(Vehicle vehicle,int VehicleId);
    Response deleteVehicle(int VehicleId);
    Response getAllVehicles();


    Response findVehicle(String vehicleType);
}
