package org.example.fuel_management_system.Repository;

import jdk.jfr.Registered;
import org.example.fuel_management_system.model.Registeredvehicles;
import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RegisteredVehicleRepository extends JpaRepository<Registeredvehicles,Integer> {
    Optional<Registeredvehicles> findByVehicleRegNo(String vehicleRegNo);




}
