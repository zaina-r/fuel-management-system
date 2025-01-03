package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.ExistingStations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExistingStationsRepository extends JpaRepository<ExistingStations, Integer> {

}
