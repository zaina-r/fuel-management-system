package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/stationCreation")
public class FuelController {
    @Autowired
    private FuelService fuelService;

    @PostMapping("/{stationId}/fuels/{petrolCapacity}/{diselCapacity}")
    public ResponseEntity<Fuel> getFuelsByStation(@PathVariable int stationId, @PathVariable float petrolCapacity, @PathVariable float diselCapacity) {
        Fuel fuels =fuelService.addFuel(stationId,petrolCapacity,diselCapacity);
        return ResponseEntity.ok(fuels);
    }

}
