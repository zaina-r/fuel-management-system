package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.VehiclesDto;
import org.example.fuel_management_system.Repository.VehicleRepository;
import org.example.fuel_management_system.model.Vehicle;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleHandler implements VehicleService{

    @Autowired
    private VehicleRepository vehicleRepository;
    @Override
    public Response addVehicle(Vehicle vehicle) {
        Response response=new Response();
       Vehicle vehicle1= vehicleRepository.save(vehicle);
       response.setMessage("Vehicle Fuel Type added successfully");
       response.setVehicleDto(MapUtils.mapVehicleEntityToVehicleDto(vehicle1));
       response.setStatusCode(200);
       return response;
    }

    @Override
    public Response getVehicle(int vehicleId) {
        Response response=new Response();
        Optional<Vehicle> vehicle1=vehicleRepository.findById(vehicleId);
        if(vehicle1.isPresent()){

            response.setMessage("Vehicle  Type can be found successfully");
            response.setVehicleDto(MapUtils.mapVehicleEntityToVehicleDto(vehicle1.get()));
            response.setStatusCode(200);

        }
        else{
            response.setStatusCode(500);
            response.setMessage("Vehicle Fuel Type not found");
        }
        return response;
    }

    @Override
    public Response updateVehicle(Vehicle vehicle,int vehicleId) {
        Response response=new Response();
        Optional<Vehicle> vehicle1=vehicleRepository.findById(vehicleId);
        if(vehicle1.isPresent()){
            vehicle1.get().setFuelCapacity(vehicle.getFuelCapacity());
            Vehicle vehicle2=vehicleRepository.save(vehicle1.get());
            response.setMessage("Vehicle Fuel Type updated successfully");
            response.setVehicleDto(MapUtils.mapVehicleEntityToVehicleDto(vehicle2));
            response.setStatusCode(200);

        }
        else{
            response.setStatusCode(500);
            response.setMessage("Vehicle Fuel Type not found");
        }
        return response;


    }

    @Override
    public Response deleteVehicle(int VehicleId) {
        Response response=new Response();
        Optional<Vehicle> vehicle1=vehicleRepository.findById(VehicleId);
        if(vehicle1.isPresent()){
           vehicleRepository.delete(vehicle1.get());
           response.setMessage("Vehicle Fuel Type deleted successfully");
           response.setStatusCode(200);

        }
        else{
            response.setStatusCode(500);
            response.setMessage("Vehicle Fuel Type not found");
        }
        return response;

    }
    public Response getAllVehicles() {
        Response response=new Response();
        List<Vehicle> vehicles=vehicleRepository.findAll();
        response.setStatusCode(200);
        response.setMessage("Vehicle Fuel Type found");
        response.setVehicleDtoList(MapUtils.mapListVehicleEntityToVehicleDto(vehicles));
        return response;

    }
}
