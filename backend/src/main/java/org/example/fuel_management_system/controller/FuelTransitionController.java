package org.example.fuel_management_system.controller;


import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.service.FuelTransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/fueltransition")
public class FuelTransitionController {

    @Autowired
    private FuelTransitionService fuelTransitionService;

    @GetMapping("/{stationId}/transitions")
    public ResponseEntity<Response> getFuelTransitionsByStationId(@PathVariable String stationId){
        Response response=fuelTransitionService.getAllFuelTransitionsByStationId(stationId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/alltransitions")
    public ResponseEntity<Response> getFuelTransitions(){
        Response response=fuelTransitionService.getAllFuelTransitions();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }



}
