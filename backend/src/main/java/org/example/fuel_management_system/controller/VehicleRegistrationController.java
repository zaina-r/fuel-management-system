package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.Repository.RegisteredVehicleRepository;
import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.Registeredvehicles;
import org.example.fuel_management_system.model.VehicleVerification;
import org.example.fuel_management_system.service.VehicleRegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@RestController
@RequestMapping("/api")
public class VehicleRegistrationController {

    // Inject the repository for database access

    @Autowired
    private VehicleRegistrationService vehicleRegistrationService;

    // The constructor is no longer required to inject the controller itself


    @PostMapping("/verifyAndAddVehicle")
    public ResponseEntity<VehicleVerification> verifyAndAddVehicle(@RequestBody VehicleVerification inputVehicle) {
           return ResponseEntity.ok(vehicleRegistrationService.verifyAndAddVehicle(inputVehicle));

    }
    @GetMapping("/{qrCode}")
    public ResponseEntity<VehicleVerification> getVehicleDetails(@PathVariable String qrCode) {
        VehicleVerification vehicle = vehicleRegistrationService.getVehicleByQrCode(qrCode);
        return ResponseEntity.ok(vehicle);
    }

    @PostMapping("/{id}/update-fuel")
    public ResponseEntity<VehicleVerification> updateFuelCapacity(@PathVariable Integer id, @RequestBody float request) {
        VehicleVerification updatedVehicle = vehicleRegistrationService.updateFuelCapacity(id, (int) request);
        return ResponseEntity.ok(updatedVehicle);

    }
}
