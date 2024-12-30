package org.example.fuel_management_system.Repository;

import jdk.jfr.Registered;
import org.example.fuel_management_system.model.Registeredvehicles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisteredVehicleRepository extends JpaRepository<Registeredvehicles,Integer> {
}
