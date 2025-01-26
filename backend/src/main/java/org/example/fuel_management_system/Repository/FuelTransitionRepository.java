package org.example.fuel_management_system.Repository;

import jdk.jfr.Registered;
import org.example.fuel_management_system.model.FuelTransition;
import org.example.fuel_management_system.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Registered
public interface FuelTransitionRepository extends JpaRepository<FuelTransition,Integer> {


    List<FuelTransition> findByStationId(String stationId);
}
