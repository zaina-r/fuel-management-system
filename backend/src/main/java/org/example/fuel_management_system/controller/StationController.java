package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.StationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class StationController {

    @Autowired
    private StationService stationService;

    public ResponseEntity<String> registerStation(int stationId,
                                                  String dealerName,
                                                  String address){
        if(stationService.doesStationIdExist(stationId)){
            return new ResponseEntity<>("Station is already registered!", HttpStatus.BAD_REQUEST);
        }
        Station station = new Station();
        station.setStationId(stationId);
        station.setStationAddress(address);
        station.setDealerName(dealerName);

        stationService.saveStation(station);
        return new ResponseEntity<>("Station registered Successfully!", HttpStatus.OK);
    }
}
