package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.ExistingStations;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.ExistingStationsServiceImpl;
import org.example.fuel_management_system.service.FuelService;
import org.example.fuel_management_system.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    FuelService fuelService;

    @Autowired
    StationService stationService;

    @Autowired
    ExistingStationsServiceImpl existingStationsService;

    @GetMapping("/stationInfo")
    public ResponseEntity<List<Station>> getAllStations() {
        return new ResponseEntity<>(stationService.getAllStations(), HttpStatus.OK);
    }

    @GetMapping("/getFuelQuantities")
    public ResponseEntity<List<Fuel>> getFuelQuantities(){
        return new ResponseEntity<>(fuelService.getFuelQuantities(), HttpStatus.OK);
    }

    @PostMapping("/addStation")
    public ResponseEntity<ExistingStations> addStation(@RequestBody ExistingStations existingStation){
        ExistingStations existingStations1 = existingStationsService.addStation(existingStation);
        return new ResponseEntity<>(existingStations1, HttpStatus.OK);
    }


}
