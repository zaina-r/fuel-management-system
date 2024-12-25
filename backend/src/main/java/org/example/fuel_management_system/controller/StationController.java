package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.StationService;
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

    @PostMapping("/registration")
    public ResponseEntity<String> registerStation(@RequestBody Station station){

        if(stationService.doesStationIdExist(station.getStationId())){
            return new ResponseEntity<>("Station is already registered!", HttpStatus.BAD_REQUEST);
        }
//        Station station = new Station();
//        station.setRegistrationDate(LocalDate.now());
//        station.setStationId(stationId);
//        station.setStationAddress(address);
//        station.setDealerName(dealerName);

//        stationService.saveStation(station);
        stationService.saveStation(station);
        return new ResponseEntity<>("Station registered Successfully!", HttpStatus.OK);
    }
}
