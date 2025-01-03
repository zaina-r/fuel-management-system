package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/station")
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }




    @GetMapping("/{stationId}/fuels")
    public ResponseEntity<List<Fuel>> getFuelsByStation(@PathVariable int stationId) {
        List<Fuel> fuels = stationService.getFuelsByStationId(stationId);
        return ResponseEntity.ok(fuels);
    }

    @PostMapping("/{stationId}/fuels")
    public ResponseEntity<String > addFuelsToStation(
            @PathVariable int stationId,
            @RequestBody List<Fuel> fuels) {
        Station updatedStation = stationService.addFuelToStation(stationId, fuels);
        return ResponseEntity.ok("Fuel Added successfully");
    }


    @PostMapping("/registration")
    public ResponseEntity<String> registerStation(@RequestBody Station station){

        if(stationService.doesStationIdExist(station.getDealerName())){
            return new ResponseEntity<>("Station is already registered!", HttpStatus.BAD_REQUEST);
        }


        if(!stationService.isStationIdValid(station.getStationId())){
            return  new ResponseEntity<>("Station does not Exist!", HttpStatus.BAD_REQUEST);
        }

        Station registeredStations=stationService.saveStation(station);

        return new ResponseEntity<>("Station registered Successfully!", HttpStatus.OK);
    }

    @GetMapping("/allstations")
    public List<Station> allStations(){
          return stationService.getAllStations();
    }

    @GetMapping("/stations/{id}")
    public Station findStationById(@PathVariable int id)
    {
        return stationService.findStationById(id);
    }

}
