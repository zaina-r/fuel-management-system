package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.ExistingStations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExistingStationsRepository extends JpaRepository<ExistingStations, Integer> {

    boolean existsByDealerId(int dealer_id);

}
