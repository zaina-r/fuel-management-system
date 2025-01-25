package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.StationFuelDto;
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
    StationService stationService;

    @GetMapping("/stationDetails")
    public List<StationFuelDto> getStationDetails() {
        return stationService.getStationFuelData();
    }
}
