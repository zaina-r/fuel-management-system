package org.example.fuel_management_system.controller;


import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Request.FuelRequest;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.service.FuelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fuel")
public class FuelController {

 @Autowired
 private FuelService fuelService;

    @PostMapping("/addfuel/{stationId}")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> addFuel(@PathVariable int stationId, @RequestBody Fuel fuel){
        Response response=fuelService.addFuel(stationId,fuel.getAvailablePetrolQuantity(),fuel.getAvailableDieselQuantity());
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @PostMapping("/updatefuel/{stationId}")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> updateFuel(@PathVariable int stationId, @RequestBody FuelRequest request){
            Response response=fuelService.updateFuel(stationId,request);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/allFuels/{stationId}")
    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER', 'ADMIN')")
    public ResponseEntity<Response> allStations(@PathVariable int stationId){
        Response response=fuelService.fuelDetails(stationId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
