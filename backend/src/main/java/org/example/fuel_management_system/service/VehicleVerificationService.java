package org.example.fuel_management_system.service;


import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface VehicleVerificationService {



 VehicleVerification addVehicle(VehicleVerification inputVehicle) ;

    public List<VehicleVerification> findAllVehicles();
}
