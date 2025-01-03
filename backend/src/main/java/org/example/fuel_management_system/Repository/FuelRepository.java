package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.Fuel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FuelRepository extends JpaRepository<Fuel,Integer> {
    List<Fuel> findByStation_StationId(int stationId);
}