package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuelStationRepository extends JpaRepository<Station, Integer> {
    boolean existsByStationId(int stationId);
}
