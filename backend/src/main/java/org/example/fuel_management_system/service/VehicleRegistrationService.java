package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.VehiclesDto;
import org.example.fuel_management_system.Qrcode.Qrcode;
import org.example.fuel_management_system.Repository.RegisteredVehicleRepository;
import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.Registeredvehicles;
import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class VehicleRegistrationService {


    private VehicleVerificationRepository vehicleVerificationRepository;
    private RegisteredVehicleRepository registeredVehicleRepository;
    private Qrcode qrcode;

    public VehicleRegistrationService(VehicleVerificationRepository vehicleVerificationRepository, RegisteredVehicleRepository registeredVehicleRepository) {
        this.vehicleVerificationRepository = vehicleVerificationRepository;
        this.registeredVehicleRepository = registeredVehicleRepository;
    }

    public Response verifyAndAddVehicle(VehicleVerification inputVehicle) {
        Response response = new Response();

        try {
            requireNonNull(inputVehicle, "Input vehicle cannot be null");

            switch (inputVehicle.getVehicle_type()) {
                case "Car":
                    inputVehicle.setMaximumFuelCapacity(5L);
                    inputVehicle.setAvailableFuelCapacity(5L);
                    break;
                case "Motorcycle":
                    inputVehicle.setMaximumFuelCapacity(3L);
                    inputVehicle.setAvailableFuelCapacity(3L);
                    break;
                case "Van":
                    inputVehicle.setMaximumFuelCapacity(6L);
                    inputVehicle.setAvailableFuelCapacity(6L);
                    break;
                case "Truck":
                    inputVehicle.setMaximumFuelCapacity(8L);
                    inputVehicle.setAvailableFuelCapacity(8L);
                    break;
                default:
                    response.setMessage("Invalid vehicle type");
                    response.setStatusCode(400);
                    return response;
            }

            inputVehicle.setQrCode(qrcode.generateQrCode());

            Optional<VehicleVerification> verifyVehicle = vehicleVerificationRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
            if (verifyVehicle.isPresent()) {
                response.setMessage("Vehicle already verified");
                response.setStatusCode(409);

            }

            Optional<Registeredvehicles> registeredVehicle = registeredVehicleRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
            if (registeredVehicle.isPresent()) {
                String licencePlateNo = registeredVehicle.get().getLicencePlateNo();

                if (inputVehicle.getLicense_plate_no().equals(licencePlateNo)) {
                    vehicleVerificationRepository.save(inputVehicle);

                    VehiclesDto vehiclesDto = new VehiclesDto();
                    vehiclesDto.setVehicleRegNo(inputVehicle.getVehicleRegNo());
                    vehiclesDto.setVehicle_type(inputVehicle.getVehicle_type());
                    vehiclesDto.setMaximumFuelCapacity(inputVehicle.getMaximumFuelCapacity());
                    vehiclesDto.setAvailableFuelCapacity(inputVehicle.getAvailableFuelCapacity());
                    vehiclesDto.setQrCode(inputVehicle.getQrCode());
                    vehiclesDto.setFuel_type(inputVehicle.getFuel_type());
                    vehiclesDto.setLicense_plate_no(inputVehicle.getLicense_plate_no());

                    response.setVehiclesDto(vehiclesDto);

                    response.setMessage("Vehicle verified and added successfully");
                    response.setStatusCode(200);

                } else {
                    response.setMessage("License plate number mismatch");
                    response.setStatusCode(400);

                }
            }

//            response.setMessage("Vehicle not registered");
//            response.setStatusCode(404);


        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setStatusCode(500);

        }
        return response;
    }

    public Response getVehicleByQrCode(String qrCode) {
        Response response = new Response();

        try {
            Optional<VehicleVerification> vehicle = vehicleVerificationRepository.findByQrCode(qrCode);
            if (vehicle.isPresent()) {
                VehiclesDto vehiclesDto = new VehiclesDto();
                vehiclesDto.setVehicleRegNo(vehicle.get().getVehicleRegNo());
                vehiclesDto.setVehicle_type(vehicle.get().getVehicle_type());
                vehiclesDto.setMaximumFuelCapacity(vehicle.get().getMaximumFuelCapacity());
                vehiclesDto.setAvailableFuelCapacity(vehicle.get().getAvailableFuelCapacity());

                response.setVehiclesDto(vehiclesDto);
                response.setMessage("Vehicle found successfully");
                response.setStatusCode(200);

            } else {
                response.setMessage("Vehicle not found");
                response.setStatusCode(404);

            }
        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setStatusCode(500);

        }
        return response;
    }

    public Response updateFuelCapacity(Integer id, float fuelDispensed) {
        Response response = new Response();

        try {
            VehicleVerification vehicle = vehicleVerificationRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Vehicle not found"));

            if (vehicle.getAvailableFuelCapacity() < fuelDispensed) {
                response.setMessage("Insufficient fuel capacity available");
                response.setStatusCode(400);

            }

            vehicle.setAvailableFuelCapacity(vehicle.getAvailableFuelCapacity() - fuelDispensed);
            vehicleVerificationRepository.save(vehicle);

            VehiclesDto vehiclesDto = new VehiclesDto();
            vehiclesDto.setVehicleRegNo(vehicle.getVehicleRegNo());
            vehiclesDto.setVehicle_type(vehicle.getVehicle_type());
            vehiclesDto.setMaximumFuelCapacity(vehicle.getMaximumFuelCapacity());
            vehiclesDto.setAvailableFuelCapacity(vehicle.getAvailableFuelCapacity());

            response.setVehiclesDto(vehiclesDto);
            response.setMessage("Fuel capacity updated successfully");
            response.setStatusCode(200);

        } catch (RuntimeException e) {
            response.setMessage(e.getMessage());
            response.setStatusCode(404); // Not Found

        } catch (Exception e) {
            response.setMessage("An error occurred: " + e.getMessage());
            response.setStatusCode(500);

        }
        return response;
    }

}

