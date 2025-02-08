package org.example.fuel_management_system.controller;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.service.AdminService;
import org.example.fuel_management_system.service.FuelService;
import org.example.fuel_management_system.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


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
//    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public ResponseEntity<Response> getAllStations() {
        Response response = stationService.getAllStations();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/getFuelQuantities")
//    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER', 'ADMIN')")
    public ResponseEntity<Response> getFuelQuantities() {
        Response response = fuelService.getFuelQuantities();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/get-stations-with-status")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> setStationsWithStatus() {
        Response response = adminService.getStationWithStatus();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/update-initial-fuel-allocation/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Response> updateInitialFuelAllocation(@RequestBody Fuel fuelAllocation, @PathVariable int id) {
        Response response = adminService.updateInitialFuelAllocation(fuelAllocation, id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @DeleteMapping("/stations/delete/{id}")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<?> deleteStationById(@PathVariable int id) {
        Response response = stationService.deleteStationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
