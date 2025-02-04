package org.example.fuel_management_system.service;


import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.Repository.FuelPriceRepository;
import org.example.fuel_management_system.Request.FuelPriceRequest;
import org.example.fuel_management_system.model.FuelPrice;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FuelPriceUpdater implements FuelPriceService {
    @Autowired
    private FuelPriceRepository fuelPriceRepository;


    public Response updateFuelPrice(FuelPriceRequest fuelPriceRequest, int id){
        Response response = new Response();
        try {
            FuelPrice fuelPrice = fuelPriceRepository.findById(id).orElseThrow(() -> new RuntimeException("Fuel not found"));
            fuelPrice.setPrice(fuelPriceRequest.getPrice());
            fuelPriceRepository.save(fuelPrice);

            response.setMessage("Successfully updated the fuel price");
            response.setStatusCode(200);
            response.setFuelPriceDto(MapUtils.mapFuelPriceEntityToFuelPriceDTO(fuelPrice));
        }catch (Exception e){
            response.setMessage("Not updated the fuel price");
            response.setStatusCode(500);
        }
        return response;

    }

    public Response getAllFuelDetails(){
        Response response = new Response();
        try {
            List<FuelPrice> fuelPrice = fuelPriceRepository.findAll();
            response.setMessage("Successfully updated the fuel price");
            response.setStatusCode(200);
            response.setFuelPriceDtoList(MapUtils.mapListFuelPriceEntityToFuelPriceDTO(fuelPrice));
        }catch (Exception e){
            response.setMessage("Not updated the fuel price");
            response.setStatusCode(500);
        }
        return response;

    }


    public Response addFuel(FuelPrice fuelPriceRequest) {
        Response response = new Response();
        try {
            FuelPrice fuel = new FuelPrice();
            fuel.setFuelName(fuelPriceRequest.getFuelName());
            fuel.setPrice(fuelPriceRequest.getPrice());
            fuel.setfId(fuelPriceRequest.getfId());

            FuelPrice savedFuelPrice = fuelPriceRepository.save(fuel);


            response.setStatusCode(200);
            response.setMessage("Fuel price added successfully.");
            response.setFuelPriceDto(MapUtils.mapFuelPriceEntityToFuelPriceDTO(savedFuelPrice));
        } catch (Exception e) {

            response.setStatusCode(500);
            response.setMessage("Failed to add fuel price: " + e.getMessage());
        }
        return response;
    }


}
