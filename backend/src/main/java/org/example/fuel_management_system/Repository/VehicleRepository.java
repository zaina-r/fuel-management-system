package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    boolean existsByVehicle_reg_no(String Vehicle_reg_no);
}