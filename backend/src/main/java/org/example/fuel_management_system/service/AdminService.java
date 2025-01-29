package org.example.fuel_management_system.service;


import org.example.fuel_management_system.DTO.FuelDto;
import org.example.fuel_management_system.DTO.Response;

import org.example.fuel_management_system.DTO.StationWithStatusDTO;
import org.example.fuel_management_system.Repository.*;
import org.example.fuel_management_system.model.*;

import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AdminService {

    private final FuelStationRepository fuelStationRepository;
    private final ExistingStationsRepository existingStationsRepository;
    private final FuelRepository fuelRepository;
    private final FuelAllocationRepository fuelAllocationRepository;
    private final StationWithRegistrationStatusRepository stationWithRegistrationStatusRepository;

    public AdminService(FuelStationRepository fuelStationRepository, ExistingStationsRepository existingStationsRepository, FuelRepository fuelRepository, FuelAllocationRepository fuelAllocationRepository, StationWithRegistrationStatusRepository stationWithRegistrationStatusRepository){
        this.fuelStationRepository = fuelStationRepository;
        this.existingStationsRepository = existingStationsRepository;
        this.fuelRepository = fuelRepository;
        this.fuelAllocationRepository = fuelAllocationRepository;
        this.stationWithRegistrationStatusRepository = stationWithRegistrationStatusRepository;
    }




    public Response getStationWithStatus() {
        Response response = new Response();

        try {
            List<ExistingStations> allStations = existingStationsRepository.findAll();
            List<StationWithRegistrationStatus> stationList = new ArrayList<>();

            if (!allStations.isEmpty()) {
                stationWithRegistrationStatusRepository.deleteAll();
                for (ExistingStations station : allStations) {
                    if (station != null) {
                        String dealerId = station.getDealerId();
                        String dealerName = station.getDealer_name() != null ? station.getDealer_name() : "Unknown Dealer";
                        boolean isRegistered = fuelStationRepository.existsByStationId(dealerId);
                        String status = isRegistered ? "Registered" : "Not Registered";

                        // Check if this station already exists in the table
                        Optional<StationWithRegistrationStatus> existingRecord =
                                stationWithRegistrationStatusRepository.findByStationId(dealerId);

                        StationWithRegistrationStatus stationWithStatus = existingRecord.orElse(new StationWithRegistrationStatus());

                        // Update or set details
                        stationWithStatus.setDealerId(dealerId);
                        stationWithStatus.setDealerName(dealerName);
                        stationWithStatus.setStatus(status);

                        stationList.add(stationWithStatus);
                    }
                }

                if (!stationList.isEmpty()) {
                    // Save or update all stations at once
                    stationWithRegistrationStatusRepository.saveAll(stationList);
                }

                List<StationWithRegistrationStatus> allStationsWithStatus = stationWithRegistrationStatusRepository.findAll();
                List<StationWithStatusDTO> dtoList = MapUtils.mapListStationWithStatusDTOToListStationWithStatus(allStationsWithStatus);

                response.setStationWithStatusDTOList(dtoList);
                response.setStatusCode(200);
                response.setMessage("Successfully retrieved and updated stations with status.");
            } else {
                response.setStatusCode(404);
                response.setMessage("No stations found.");
                response.setStationWithStatusDTOList(new ArrayList<>());
            }
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setMessage("An error occurred while retrieving stations: " + e.getMessage());
            response.setStationWithStatusDTOList(new ArrayList<>());
        }

        return response;
    }







    public Response updateInitialFuelAllocation(Fuel fuelAllocation,int id) {
            Response response = new Response();

            try {

                Station station=fuelStationRepository.findById(id).get();
                Fuel fuel = fuelRepository.findByStationId(id);
                if (fuel == null) {
                    fuel = new Fuel();
                    fuel.setStation(station);
                }


                fuel.setAvailableDieselQuantity(fuelAllocation.getInitialDieselAllocation());
                fuel.setAvailablePetrolQuantity(fuelAllocation.getInitialPetrolAllocation());
                fuel.setInitialDieselAllocation(fuelAllocation.getInitialDieselAllocation());
                fuel.setInitialPetrolAllocation(fuelAllocation.getInitialPetrolAllocation());

                fuelRepository.save(fuel);

                response.setMessage("Fuel allocation updated successfully.");
                response.setStatusCode(200);
                response.setFuelDto(MapUtils.mapFuelEntityToFuelDTO(fuel));
            } catch (Exception e) {
                response.setMessage("An error occurred while updating fuel allocation: " + e.getMessage());
                response.setStatusCode(500);
            }
            return response;
        }
    }






