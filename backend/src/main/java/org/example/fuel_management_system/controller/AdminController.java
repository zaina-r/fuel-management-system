package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    FuelService fuelService;

    @GetMapping("/fuelAmount")
    public ResponseEntity<List<Station>> getAllStations() {
        return new ResponseEntity<>(fuelService.getAllStations(), HttpStatus.OK);
    }
}
