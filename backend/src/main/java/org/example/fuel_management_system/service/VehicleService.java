package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.VehicleRepository;
import org.example.fuel_management_system.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
    @Autowired
    final VehicleRepository vehicleRepository;

    public VehicleService(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }

    public boolean isVehicleExisits(String Vehicle_reg_no) {
        return vehicleRepository.existsByVehicle_reg_no(Vehicle_reg_no);
    }
}
