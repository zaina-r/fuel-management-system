package org.example.fuel_management_system.controller;

import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.service.StationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/station")
public class StationController {

    private final StationService stationService;
    private final FuelStationRepository fuelStationRepository;

    public StationController(StationService stationService, FuelStationRepository fuelStationRepository) {
        this.stationService = stationService;
        this.fuelStationRepository = fuelStationRepository;
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
    public ResponseEntity<String> registerStation(@RequestBody Station station) throws Exception {
        try{
        if(fuelStationRepository.existsByStationId(station.getStationId())){
            throw new Exception("Station already exists");
        }

        Station registeredStations=stationService.saveStation(station);

        return new ResponseEntity<>("Station registered Successfully!", HttpStatus.OK);
    }catch (Exception e){
            return new ResponseEntity<>("Station already exists",HttpStatus.BAD_REQUEST);
        }

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
