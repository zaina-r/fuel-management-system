package org.example.fuel_management_system.controller;


import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.StationWithRegistrationStatus;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.FuelAllocation;
import org.example.fuel_management_system.service.AdminService;
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

    AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

   @GetMapping("/stationInfo")
    public ResponseEntity<Response> getAllStations() {
        Response response= stationService.getAllStations();
       return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getFuelQuantities")
    public ResponseEntity<Response> getFuelQuantities(){
        Response response= fuelService.getFuelQuantities();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/get-stations-with-status")
    public List<StationWithRegistrationStatus> getStationsWithStatus() {

        return adminService.getStationWithStatus();
    }

    @PostMapping("/update-weekly-fuel-allocation")
    public ResponseEntity<Response> updateWeeklyFuelAllocation(@RequestBody FuelAllocation fuelAllocation){
        Response response=adminService.updateWeeklyFuelAllocation(fuelAllocation);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
