package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Request.FuelPriceRequest;
import org.example.fuel_management_system.model.FuelPrice;
import org.example.fuel_management_system.service.FuelPriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class FuelPriceController {

    @Autowired
    private FuelPriceService fuelPriceService;


    @GetMapping("/allfuelPrices")
//    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER', 'ADMIN')")
    public ResponseEntity<Response> getAllFuelPrices(){
        Response response=fuelPriceService.getAllFuelDetails();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PutMapping("/updatePrice/{fuelPriceId}")
//    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> UpdatePrice(@RequestBody FuelPrice fuelPriceRequest,@PathVariable int fuelPriceId){
        System.out.println(fuelPriceRequest.getFuelName());
        Response response=fuelPriceService.updateFuelPrice(fuelPriceRequest,fuelPriceId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/addFuel")
//    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> addFuel(@RequestBody FuelPrice fuelPriceRequest){
        Response response=fuelPriceService.addFuel(fuelPriceRequest);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @GetMapping("/findfuel/{fuelId}")
//    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> findFuel(@PathVariable String fuelId){
        System.out.println(fuelId);
        Response response=fuelPriceService.findFuel(fuelId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}
