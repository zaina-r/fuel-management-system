package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FuelStationRepository extends JpaRepository<Station, Integer> {

    Optional<Station> findByDealerName(String stationName);
}
