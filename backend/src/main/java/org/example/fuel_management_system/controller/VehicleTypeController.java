package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.Vehicle;
import org.example.fuel_management_system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VehicleTypeController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicleType/{id}")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> getVehicleType(@PathVariable int id) {
        Response response=vehicleService.getVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/vehicleTypes")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> getVehicleTypes() {
        Response response=vehicleService.getAllVehicles();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PostMapping("/vehicleTypes/add")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> addVehicleType(@RequestBody Vehicle vehicle) {
        Response response=vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PutMapping("/vehicleTypes/update/{id}")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> updateVehicleFuel(@RequestBody Vehicle vehicle,@PathVariable int id) {
        Response response=vehicleService.updateVehicle(vehicle,id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @DeleteMapping("/vehicleType/delete/{id}")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> deleteVehicleType(@PathVariable int id) {
        Response response=vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/vehicle/{fuelType}")
    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> getFuelType(@PathVariable String fuelType) {
        Response response=vehicleService.findVehicle(fuelType);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
