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
import org.springframework.web.bind.annotation.PathVariable;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

import static java.util.Objects.requireNonNull;

@Service
public interface VehicleRegistrationService {

 Response verifyAndAddVehicle(VehicleVerification inputVehicle,int userId) ;

     Response getVehicleByQrCode(String qrCode);

 Response updateFuelCapacity(Integer id, float fuelDispensed);
 Response allVehicleDetails(int id);
 Response getAllVehicles();

}

