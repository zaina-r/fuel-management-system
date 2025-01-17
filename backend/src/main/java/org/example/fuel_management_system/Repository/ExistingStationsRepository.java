package org.example.fuel_management_system.Repository;

import org.example.fuel_management_system.model.ExistingStations;
import org.example.fuel_management_system.model.Station;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ExistingStationsRepository extends JpaRepository<ExistingStations, Integer> {
    boolean existsByLicenseNumber(String LicenseNumber);
    @Query("SELECT s FROM Station s WHERE s.licenseNumber = :licenseNumber")
    Optional<Station> findByLicenseNumber(String licenseNumber);
}
