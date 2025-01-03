package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleVerificationService {
    @Autowired
    private VehicleVerificationRepository vehicleVerificationRepository;


    public VehicleVerification addVehicle(VehicleVerification inputVehicle) {
        return vehicleVerificationRepository.save(inputVehicle);
    }

    public List<VehicleVerification> findAllVehicles() {
        return vehicleVerificationRepository.findAll();
    }
}
