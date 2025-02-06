package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.Vehicle;
import org.example.fuel_management_system.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class VehicleTypeController {

    @Autowired
    private VehicleService vehicleService;

    @GetMapping("/vehicleType/{id}")
    public ResponseEntity<Response> getVehicleType(@PathVariable int id) {
        Response response=vehicleService.getVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/vehicleTypes")
    public ResponseEntity<Response> getVehicleTypes() {
        Response response=vehicleService.getAllVehicles();
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PostMapping("/vehicleTypes/add")
    public ResponseEntity<Response> addVehicleType(@RequestBody Vehicle vehicle) {
        Response response=vehicleService.addVehicle(vehicle);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PutMapping("/vehicleTypes/update/{id}")
    public ResponseEntity<Response> updateVehicleFuel(@RequestBody Vehicle vehicle,@PathVariable int id) {
        Response response=vehicleService.updateVehicle(vehicle,id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @DeleteMapping("/vehicleType/delete/{id}")
    public ResponseEntity<Response> deleteVehicleType(@PathVariable int id) {
        Response response=vehicleService.deleteVehicle(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/vehicle/{fuelType}")
    public ResponseEntity<Response> getFuelType(@PathVariable String fuelType) {
        Response response=vehicleService.findVehicle(fuelType);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
