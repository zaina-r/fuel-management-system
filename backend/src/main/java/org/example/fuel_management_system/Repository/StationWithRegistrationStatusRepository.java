package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.StationWithRegistrationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StationWithRegistrationStatusRepository extends JpaRepository<StationWithRegistrationStatus,Integer> {
    boolean existsByStationId(String stationId);

    Optional<StationWithRegistrationStatus> findByStationId(String dealerId);
}
