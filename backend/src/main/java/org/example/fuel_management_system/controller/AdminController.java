package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
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



  /*  @GetMapping("/stationInfo")
    public ResponseEntity<Response> getAllStations() {
        return new ResponseEntity<>(stationService.getAllStations(), HttpStatus.OK);
    }*/

    @GetMapping("/getFuelQuantities")
    public ResponseEntity<List<Fuel>> getFuelQuantities(){
        return new ResponseEntity<>(fuelService.getFuelQuantities(), HttpStatus.OK);
    }

   /* public ResponseEntity<Station> addProduct(@RequestBody Station station){
        Station station1 = stationService.addStation(station);
        return new ResponseEntity<>(station1, HttpStatus.OK);
    }*/
}
