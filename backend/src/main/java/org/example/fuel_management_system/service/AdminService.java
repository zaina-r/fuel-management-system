package org.example.fuel_management_system.service;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.*;
import org.springframework.stereotype.Service;



@Service
public interface AdminService {
     Response getStationWithStatus();
     Response updateInitialFuelAllocation(Fuel fuelAllocation, int id);
}



