package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
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
    private VehicleVerificationRepository vehicleVerificationRepository;

    // The constructor is no longer required to inject the controller itself
    public VehicleRegistrationController() {
    }

    @PostMapping("/verifyUser")
    public String verifyVehicle(@RequestBody VehicleVerification inputVehicle) {
        requireNonNull(inputVehicle, "Input vehicle cannot be null");

        // Find vehicle by vehicle registration number
        return vehicleVerificationRepository.findByVehicleRegNo(inputVehicle.getVehicle_reg_no())
                .map(vehicleVerification -> {
                    // Check if the registration number matches
                    if (vehicleVerification.getVehicle_reg_no().equals(inputVehicle.getVehicle_reg_no())) {
                        return "Vehicle verified successfully!";
                    } else {
                        return "Vehicle found, but registration number does not match.";
                    }
                })
                .orElse("Vehicle not found in the database.");
    }
}
