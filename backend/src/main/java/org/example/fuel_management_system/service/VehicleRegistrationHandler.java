package org.example.fuel_management_system.service;

import org.example.fuel_management_system.DTO.Response;
import org.example.fuel_management_system.DTO.VehiclesDto;
import org.example.fuel_management_system.Qrcode.Qrcode;
import org.example.fuel_management_system.Repository.RegisteredVehicleRepository;
import org.example.fuel_management_system.Repository.UserAccountRepository;
import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.Registeredvehicles;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.model.VehicleVerification;
import org.example.fuel_management_system.utilities.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class VehicleRegistrationHandler implements VehicleRegistrationService{
    private VehicleVerificationRepository vehicleVerificationRepository;
    private RegisteredVehicleRepository registeredVehicleRepository;
    private Qrcode qrcode;
    private UserAccountRepository userAccountRepository;

    public VehicleRegistrationHandler(VehicleVerificationRepository vehicleVerificationRepository, RegisteredVehicleRepository registeredVehicleRepository, Qrcode qrcode, UserAccountRepository userAccountRepository) {
        this.vehicleVerificationRepository = vehicleVerificationRepository;
        this.registeredVehicleRepository = registeredVehicleRepository;
        this.qrcode = qrcode;
        this.userAccountRepository = userAccountRepository;
    }

    public Response verifyAndAddVehicle(VehicleVerification inputVehicle, int userId,float fuelAmount) {
        Response response = new Response();


        try {
            requireNonNull(inputVehicle, "Input vehicle cannot be null");


            inputVehicle.setQrCode(qrcode.generateQrCode());
            inputVehicle.setMaximumFuelCapacity(fuelAmount);
            inputVehicle.setAvailableFuelCapacity(fuelAmount);

            Optional<VehicleVerification> verifyVehicle = vehicleVerificationRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
            if (verifyVehicle.isPresent()) {
                response.setMessage("Vehicle already verified");
                response.setStatusCode(409);
                return response;

            }

            Optional<Registeredvehicles> registeredVehicle = registeredVehicleRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
            if (registeredVehicle.isPresent()) {
                String licencePlateNo = registeredVehicle.get().getLicencePlateNo();

                if (inputVehicle.getLicense_plate_no().equals(licencePlateNo)) {

                    Optional<UserAccount> userAccount = userAccountRepository.findById(userId);
                    if (userAccount.isEmpty()) {
                        response.setMessage("User account not found");
                        response.setStatusCode(404); 
                        return response;
                    }

                    inputVehicle.setUserAccount(userAccount.get());

                    vehicleVerificationRepository.save(inputVehicle);

                    VehiclesDto vehiclesDto = new VehiclesDto();
                    vehiclesDto.setVehicleId(inputVehicle.getVehicleId());
                    vehiclesDto.setVehicleRegNo(inputVehicle.getVehicleRegNo());
                    vehiclesDto.setVehicle_type(inputVehicle.getVehicle_type());
                    vehiclesDto.setMaximumFuelCapacity(inputVehicle.getMaximumFuelCapacity());
                    vehiclesDto.setAvailableFuelCapacity(inputVehicle.getAvailableFuelCapacity());
                    vehiclesDto.setQrCode(inputVehicle.getQrCode());
                    vehiclesDto.setFuel_type(inputVehicle.getFuel_type());
                    vehiclesDto.setLicense_plate_no(inputVehicle.getLicense_plate_no());
                    vehiclesDto.setUserAccount(userAccount.get());
                    response.setVehiclesDto(vehiclesDto);

                    response.setMessage("Vehicle verified and added successfully");
                    response.setStatusCode(200);

                } else {
                    response.setMessage("License plate number mismatch");
                    response.setStatusCode(400);

                }
            }
            else{
                response.setMessage("Vehicle not registered");
                response.setStatusCode(404);
            }




        } catch (Exception e) {
            response.setMessage(e.getMessage());
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
                vehiclesDto.setLicense_plate_no(vehicle.get().getLicense_plate_no());
                vehiclesDto.setVehicle_type(vehicle.get().getVehicle_type());
                vehiclesDto.setFuel_type(vehicle.get().getFuel_type());
                vehiclesDto.setAvailableFuelCapacity(vehicle.get().getAvailableFuelCapacity());
                vehiclesDto.setVehicleId(vehicle.get().getVehicleId());
                vehiclesDto.setUserAccount(vehicle.get().getUserAccount());

                response.setVehiclesDto(vehiclesDto);
                response.setMessage("Vehicle found successfully");
                response.setStatusCode(200);

            } else {
                response.setMessage("Vehicle not found");
                response.setStatusCode(404);

            }
        } catch (Exception e) {
            response.setMessage("Invalid vehicle registration number and chessy number  ");
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
            System.out.println(vehicle.getAvailableFuelCapacity());
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
            response.setMessage("Invalid vehicle registration number and chessy number  ");
            response.setStatusCode(404); // Not Found

        } catch (Exception e) {
            response.setMessage("Invalid vehicle registration number and chessy number  ");
            response.setStatusCode(500);

        }
        return response;
    }

    public Response allVehicleDetails(int id) {
        Response response = new Response();

        try {
            List<VehicleVerification> vehicleVerifications = vehicleVerificationRepository.findByUser_UserId(id);
            System.out.println("Vehicle verifications : " +vehicleVerifications);
            if (vehicleVerifications!=null) {
                List<VehiclesDto> vehiclesDtoList = MapUtils.mapVehicleListEntityToVehicleDTOList(vehicleVerifications);
                response.setVehiclesDtoList(vehiclesDtoList);
                response.setMessage("Vehicle details retrieved successfully");
                response.setStatusCode(200);
            } else {
                response.setMessage("No vehicles found for the given user ID");
                response.setStatusCode(404);
            }
        } catch (Exception e) {
            response.setMessage("Vehicles details cannot retrieved");
            response.setStatusCode(500);
        }

        return response;
    }
    public Response getAllVehicles() {
        Response response = new Response();
        try{
            List<VehicleVerification> vehicleVerifications = vehicleVerificationRepository.findAll();
            response.setVehiclesDtoList(MapUtils.mapVehicleListEntityToVehicleDTOList(vehicleVerifications));
            response.setMessage("Vehicles retrieved successfully");
            response.setStatusCode(200);


        }catch (RuntimeException e) {
            response.setMessage("Vehicles details cannot retrieved");
            response.setStatusCode(500);
        }
        return response;
    }
}
