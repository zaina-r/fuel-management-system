package org.example.fuel_management_system.controller;


import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.FuelTransition;
import org.example.fuel_management_system.service.FuelTransitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/fuelAllocation")
public class FuelTransitionController {

    @Autowired
    private FuelTransitionService fuelTransitionService;

    @GetMapping("/{stationId}/transitions")
    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER', 'ADMIN')")
    public ResponseEntity<Response> getFuelTransitionsByStationId(@PathVariable String stationId){
        Response response=fuelTransitionService.getAllFuelTransitionsByStationId(stationId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/alltransitions")
    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER', 'ADMIN')")
    public ResponseEntity<Response> getFuelTransitions(){
        Response response=fuelTransitionService.getAllFuelTransitions();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    @PostMapping("/add/{vehicleId}/{userId}")
    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER', 'ADMIN')")
    public ResponseEntity<Response> addFuelTransition(@RequestBody FuelTransition fuelTransition,@PathVariable int userId,@PathVariable int vehicleId){
        Response response=fuelTransitionService.addTransaction(fuelTransition,userId,vehicleId);
        return ResponseEntity.status(response.getStatusCode()).body(response);

    }



}
