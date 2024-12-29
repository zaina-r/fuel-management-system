package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.StationService;
import org.example.fuel_management_system.service.StationValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RestController
@RequestMapping("/station")
public class StationController {

    @Autowired
    private StationService stationService;

    @Autowired
    private StationValidationService stationValidationService;

    @PostMapping("/registration")
    public ResponseEntity<String> registerStation(@RequestBody Station station){

        if(stationService.doesStationIdExist(station.getStationId())){
            return new ResponseEntity<>("Station is already registered!", HttpStatus.BAD_REQUEST);
        }

        if(stationValidationService.isStationIdValid(station.getStationId())){
            return  new ResponseEntity<>("Station does not Exist!", HttpStatus.BAD_REQUEST);
        }

        station.setRegistrationDate(LocalDate.now());

        stationService.saveStation(station);

        return new ResponseEntity<>("Station registered Successfully!", HttpStatus.OK);
    }
}
