package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Integer> {
    Fuel findByStationId(int stationId);


}