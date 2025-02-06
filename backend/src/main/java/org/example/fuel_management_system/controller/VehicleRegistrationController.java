package org.example.fuel_management_system.controller;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.model.VehicleVerification;
import org.example.fuel_management_system.service.VehicleRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api")
public class VehicleRegistrationController {




    private VehicleRegistrationService vehicleRegistrationService;

    public VehicleRegistrationController(VehicleRegistrationService vehicleRegistrationService) {
        this.vehicleRegistrationService = vehicleRegistrationService;
    }

    @PostMapping("/verifyAndAddVehicle/{userId}/{fuelAmount}")
    public ResponseEntity<Response> verifyAndAddVehicle(@RequestBody VehicleVerification inputVehicle,@PathVariable int userId,@PathVariable float fuelAmount) {
        Response response= vehicleRegistrationService.verifyAndAddVehicle(inputVehicle,userId,fuelAmount);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/allVehicleDetails/{userId}")
    public ResponseEntity<Response> allVehicleDetails(@PathVariable int userId){
      Response response= vehicleRegistrationService.allVehicleDetails(userId);
      return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @GetMapping("/vehicle/getAllVehicles")
    public ResponseEntity<Response>getAllVehicles(){
        Response response= vehicleRegistrationService.getAllVehicles();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @GetMapping("/{qrCode}")
    public ResponseEntity<Response> getVehicleDetails(@PathVariable String qrCode) {
        Response response= vehicleRegistrationService.getVehicleByQrCode(qrCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping("/{id}/update-fuel")
    public ResponseEntity<Response> updateFuelCapacity(@PathVariable Integer id, @RequestBody float request) {
        Response response= vehicleRegistrationService.updateFuelCapacity(id,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
}
