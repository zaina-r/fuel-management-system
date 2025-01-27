package org.example.fuel_management_system.service;


import org.example.fuel_management_system.DTO.FuelDto;
import org.example.fuel_management_system.DTO.Response;

import org.example.fuel_management_system.DTO.StationWithStatusDTO;
import org.example.fuel_management_system.Repository.*;
import org.example.fuel_management_system.model.ExistingStations;
import org.example.fuel_management_system.model.Fuel;

import org.example.fuel_management_system.model.FuelAllocation;
import org.example.fuel_management_system.model.StationWithRegistrationStatus;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
                for (ExistingStations station : allStations) {
                    if (station != null) {

                        boolean exists = stationWithRegistrationStatusRepository.existsByStationId(station.getDealerId());
                        if (!exists) {

                            boolean isRegistered = fuelStationRepository.existsByStationId(station.getDealerId());
                            String status = isRegistered ? "Registered" : "Not Registered";

                            String dealerName = station.getDealer_name() != null ? station.getDealer_name() : "Unknown Dealer";
                            String dealerId = station.getDealerId() != null ? station.getDealerId() : "Unknown ID";

                            StationWithRegistrationStatus stationWithStatus = new StationWithRegistrationStatus();
                            stationWithStatus.setDealerId(dealerId);
                            stationWithStatus.setDealerName(dealerName);
                            stationWithStatus.setStatus(status);

                            stationList.add(stationWithStatus);
                        }
                    }
                }


                if (!stationList.isEmpty()) {
                    stationWithRegistrationStatusRepository.saveAll(stationList);
                }


                List<StationWithRegistrationStatus> allStationsWithStatus = stationWithRegistrationStatusRepository.findAll();
                System.out.println(allStationsWithStatus.get(0));


                List<StationWithStatusDTO> dtoList = MapUtils.mapListStationWithStatusDTOToListStationWithStatus(allStationsWithStatus);



                response.setStationWithStatusDTOList(dtoList);
                response.setStatusCode(200);
                response.setMessage("Successfully retrieved all stations with status.");
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






        public Response updateInitialFuelAllocation(Fuel fuelAllocation) {
            Response response = new Response();
            try {
                if (fuelAllocation.getStation() == null || fuelAllocation.getStation().getId() == 0) {
                    response.setMessage("Station or Station ID cannot be null.");
                    response.setStatusCode(400);
                    return response;
                }

                Fuel fuel = fuelRepository.findByStationId(fuelAllocation.getStation().getId());
                if (fuel == null) {
                    fuel = new Fuel();
                    fuel.setStation(fuelAllocation.getStation());
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






