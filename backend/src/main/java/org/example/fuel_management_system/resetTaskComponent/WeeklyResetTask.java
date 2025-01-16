package org.example.fuel_management_system.resetTaskComponent;

import org.example.fuel_management_system.Repository.VehicleVerificationRepository;
import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class WeeklyResetTask {
    @Autowired
    private VehicleVerificationRepository vehicleVerificationRepository;

    @Scheduled(cron = "0 0 0 * * MON") // Every Monday at midnight
    public void resetFuelCapacity() {
        List<VehicleVerification> vehicles = vehicleVerificationRepository.findAll();
        for (VehicleVerification vehicle : vehicles) {
            vehicle.setAvailableFuelCapacity(vehicle.getMaximumFuelCapacity());
        }
        vehicleVerificationRepository.saveAll(vehicles);
    }

}
