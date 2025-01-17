package org.example.fuel_management_system.service;

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

        // Generate and set the QR code for the vehicle
        inputVehicle.setQrCode(generateQrCode());

        Optional<VehicleVerification>verifyVehicle=vehicleVerificationRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
        if(verifyVehicle.isPresent()){
                return null;

            }else{
            Optional<Registeredvehicles> registeredVehicle = registeredVehicleRepository.findByVehicleRegNo(inputVehicle.getVehicleRegNo());
            System.out.println(registeredVehicle.isPresent());
            if (registeredVehicle.isPresent()) {


                String li=registeredVehicle.get().getLicencePlateNo();



                if(inputVehicle.getLicense_plate_no().equals(li)){
                    vehicleVerificationRepository.save(inputVehicle);
                    return inputVehicle;
                }

            }

        }




        return null;
    }

    public String generateQrCode() {

         String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";


         SecureRandom random = new SecureRandom();


         StringBuilder sb = new StringBuilder();

         for (int i = 0; i < 12; i++) {

             int randomIndex = random.nextInt(characters.length());
             sb.append(characters.charAt(randomIndex));
         }
         System.out.println(sb.toString());
         return sb.toString();
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
