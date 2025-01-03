package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.Repository.RegisteredVehicleRepository;
import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.Registeredvehicles;
import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/api")
public class VehicleRegistrationController {

    // Inject the repository for database access
    @Autowired
    private RegisteredVehicleRepository registeredVehicleRepository;
    @Autowired
    private VehicleVerificationRepository vehicleVerificationRepository;

    // The constructor is no longer required to inject the controller itself
    public VehicleRegistrationController() {
    }

    @PostMapping("/verifyAndAddVehicle")
    public String verifyAndAddVehicle(@RequestBody VehicleVerification inputVehicle) {
        requireNonNull(inputVehicle, "Input vehicle cannot be null");

        // Check if the vehicle registration number exists in the registered vehicle repository

        Optional<Registeredvehicles> registeredVehicle = registeredVehicleRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
        System.out.println(registeredVehicle);
        if (registeredVehicle.isPresent()) {
            // Add the vehicle to the VehicleVerification table
            vehicleVerificationRepository.save(inputVehicle);
            return "Vehicle verified and added to the Vehicle Verification table!";
        } else {
            return "Vehicle registration number not found in the registered vehicle repository.";
        }
    }
}
