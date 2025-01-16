package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.VehicleVerification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehicleVerificationRepository extends JpaRepository<VehicleVerification, Integer> {
    Optional<VehicleVerification> findByQrCode(String qrCode);
    Optional<VehicleVerification> findByVehicleRegNo(String vehicleRegNo);

}
