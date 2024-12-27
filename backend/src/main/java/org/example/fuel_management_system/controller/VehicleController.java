package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.Vehicle;
import org.example.fuel_management_system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {
    @Autowired
    private final VehicleService vehicleService;

    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @GetMapping("/{Vehicle_reg_no}")
    @CrossOrigin(origins = "*")
    public ResponseEntity<String> checkVehicle(@PathVariable String Vehicle_reg_no) {
        boolean vehicleExists = vehicleService.isVehicleExisits(Vehicle_reg_no);
        if (vehicleExists) {
            return ResponseEntity.ok("User found in the database.");
        } else {
            return ResponseEntity.status(404).body("User not found in the database.");
        }
    }
}
