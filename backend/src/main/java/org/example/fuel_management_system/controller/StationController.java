package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.Request.StationRequest;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station")
public class StationController {

    private  StationService stationService;
    private  FuelStationRepository fuelStationRepository;

    public StationController(StationService stationService, FuelStationRepository fuelStationRepository) {
        this.stationService = stationService;
        this.fuelStationRepository = fuelStationRepository;
    }


    @GetMapping("/allstations")
//    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> allStations(){
        Response response=stationService.getAllStations();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/stations/{id}")
//    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER', 'ADMIN')")
    public ResponseEntity<Response> findStationById(@PathVariable int id)
    {
        Response response=stationService.findStationById(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }
    @PutMapping("/update/{id}")
//    @PreAuthorize("hasAnyAuthority( 'ADMIN')")
    public ResponseEntity<Response> UpdateStationById(@RequestBody StationRequest stationRequest,@PathVariable int id)
    {
        Response response=stationService.updateStation(stationRequest,id);
        return ResponseEntity.status(HttpStatus.OK).body(response);

    }



    @PostMapping("/registration")
//    @PreAuthorize("hasAnyAuthority('FUELSTATION_OWNER')")
    public ResponseEntity<Response> registerStation(@RequestBody Station station) throws Exception {
  Response response=stationService.saveOrUpdateStation(station);
  return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
    @PostMapping("/mobile/{loginCode}")
    public ResponseEntity<Response>getStationUsingMobile( @PathVariable String loginCode){
        Response response=stationService.findByLoginCode(loginCode);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }





}
