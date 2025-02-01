package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Request.FuelPriceRequest;
import org.example.fuel_management_system.service.FuelPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FuelPriceController {

    @Autowired
    private FuelPriceService fuelPriceService;


    @GetMapping("/allfuelPrices")
    public ResponseEntity<Response> getAllFuelPrices(){
        Response response=fuelPriceService.getAllFuelDetails();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updatePrice/{fuelPriceId}")
    public ResponseEntity<Response> UpdatePrice(@RequestBody FuelPriceRequest fuelPriceRequest,@PathVariable int fuelPriceId){
        Response response=fuelPriceService.updateFuelPrice(fuelPriceRequest,fuelPriceId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}
