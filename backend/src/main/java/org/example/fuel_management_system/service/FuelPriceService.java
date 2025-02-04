package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Request.FuelPriceRequest;
import org.example.fuel_management_system.model.FuelPrice;

import org.springframework.stereotype.Service;

@Service
public interface FuelPriceService {


   Response updateFuelPrice(FuelPriceRequest fuelPriceRequest,int id);

 Response getAllFuelDetails();

Response addFuel(FuelPrice fuelPriceRequest);

}

