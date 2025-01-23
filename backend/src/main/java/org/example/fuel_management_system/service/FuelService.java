package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.FuelDto;
import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.StationDto;
import org.example.fuel_management_system.Repository.FuelRepository;
import org.example.fuel_management_system.Repository.FuelStationRepository;
import org.example.fuel_management_system.Request.FuelRequest;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelService {
    @Autowired
    private FuelRepository fuelRepository;
    @Autowired
    private StationService stationService;
    @Autowired
    private FuelStationRepository fuelStationRepository;

    public Response addFuel(int stationId, float petrolCapacity, float dieselCapacity) {
        Response response = stationService.findStationById(stationId);
        StationDto stationDto = response.getStationDto();
        if (stationDto == null) {
                 response.setMessage("Station not found");
                 response.setStatusCode(400);
                 return response;
        }

        Station fuelStation = new Station(
                stationDto.getId(),
                stationDto.getStationId(),
                stationDto.getStationAddress(),
                stationDto.getDealerName(),
                stationDto.getLicenseNumber(),
                stationDto.getRegistrationDate(),
                stationDto.getLoginCode(),
                stationDto.getFuel()
        );


        Fuel fuel = new Fuel();
        fuel.setStation(fuelStation);
        fuel.setAvailablePetrolQuantity(petrolCapacity);
        fuel.setAvailableDiselQuantity(dieselCapacity);


        fuel = fuelRepository.save(fuel);

        Response result = new Response();
        result.setMessage("Fuel added successfully");
        result.setStatusCode(200);
        result.setFuelDto(MapUtils.mapFuelEntityToFuelDTO(fuel));

        return result;
    }


    public List<Fuel> getFuelQuantities() {
        return fuelRepository.findAll();
    }

    public Response updateFuel(int stationId, FuelRequest request) {

        Response response = stationService.findStationById(stationId);
        Response result = new Response();
        StationDto stationDto = response.getStationDto();

        // If the station is not found
        if (stationDto == null) {
            result.setMessage("Station not found");
            result.setStatusCode(400);
            return result;
        }

        Fuel stationFuel = fuelRepository.findByStationId(stationId);
        if (stationFuel == null) {
            result.setMessage("Fuel data not available for the station");
            result.setStatusCode(400);
            return result;
        }

        String fuelType = request.getFuelType().toLowerCase(); // Normalize fuel type
        Float requestedQuantity = request.getQuantity();       // Quantity requested by the user

        if ("petrol".equals(fuelType)) {
            // Handle petrol update
            Float availablePetrol = stationFuel.getAvailablePetrolQuantity();

            if (availablePetrol < requestedQuantity) {
                result.setMessage("Insufficient petrol available");
                result.setStatusCode(400);
                return result;
            }

            // Deduct requested petrol quantity
            stationFuel.setAvailablePetrolQuantity(availablePetrol - requestedQuantity);

        } else if ("diesel".equals(fuelType)) { // Correct spelling for diesel
            // Handle diesel update
            Float availableDiesel = stationFuel.getAvailableDiselQuantity();

            if (availableDiesel < requestedQuantity) {
                result.setMessage("Insufficient diesel available");
                result.setStatusCode(400);
                return result;
            }

            // Deduct requested diesel quantity
            stationFuel.setAvailableDiselQuantity(availableDiesel - requestedQuantity);

        } else {

            result.setMessage("Invalid fuel type. Only 'petrol' or 'diesel' are allowed.");
            result.setStatusCode(400);
            return result;
        }

        Fuel updatedFuel = fuelRepository.save(stationFuel);

        result.setMessage("Fuel updated successfully");
        result.setStatusCode(200);
        result.setFuelDto(MapUtils.mapFuelEntityToFuelDTO(updatedFuel));

        return result;
    }


}
