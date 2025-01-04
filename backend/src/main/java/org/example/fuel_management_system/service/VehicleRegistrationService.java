package org.example.fuel_management_system.service;

import org.example.fuel_management_system.Repository.RegisteredVehicleRepository;
import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.Registeredvehicles;
import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public class VehicleRegistrationService {

    @Autowired
    private VehicleVerificationRepository vehicleVerificationRepository;

    @Autowired
    private RegisteredVehicleRepository registeredVehicleRepository;

    public VehicleVerification verifyAndAddVehicle(VehicleVerification inputVehicle) {
        System.out.println(inputVehicle.getLicense_plate_no());
        requireNonNull(inputVehicle, "Input vehicle cannot be null");
        if(inputVehicle.getVehicle_type().equals("Car")){
            inputVehicle.setMaximumFuelCapacity(5L);
            inputVehicle.setAvailableFuelCapacity(5L);
        }
        if(inputVehicle.getVehicle_type().equals("Motorcycle")){
            inputVehicle.setMaximumFuelCapacity(3L);
            inputVehicle.setAvailableFuelCapacity(3L);
        }
        if(inputVehicle.getVehicle_type().equals("Van")){
            inputVehicle.setMaximumFuelCapacity(6L);
            inputVehicle.setAvailableFuelCapacity(6L);
        }
        if(inputVehicle.getVehicle_type().equals("Truck")){
            inputVehicle.setMaximumFuelCapacity(8L);
            inputVehicle.setAvailableFuelCapacity(8L);
        }

        inputVehicle.setQrCode(generateQrCode(inputVehicle.getLicense_plate_no()));

        Optional<VehicleVerification>verifyVehicle=vehicleVerificationRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
        if(verifyVehicle.isPresent()){


            }else{
            Optional<Registeredvehicles> registeredVehicle = registeredVehicleRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
            System.out.println(registeredVehicle.isPresent());
            if (registeredVehicle.isPresent()) {

                vehicleVerificationRepository.save(inputVehicle);
            }

        }




        return inputVehicle;
    }
     public String generateQrCode(String LicensePlateNo) {
        // QR code generation logic using ZXing library
        return "QR_CODE_" + LicensePlateNo;
    }

    public VehicleVerification getVehicleByQrCode(String qrCode) {
        return vehicleVerificationRepository.findByQrCode(qrCode)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
    }
    public VehicleVerification updateFuelCapacity(Integer id, int fuelDispensed) {
        VehicleVerification vehicle = vehicleVerificationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));
        vehicle.setAvailableFuelCapacity(vehicle.getAvailableFuelCapacity() - fuelDispensed);
        return vehicleVerificationRepository.save(vehicle);
    }
}
