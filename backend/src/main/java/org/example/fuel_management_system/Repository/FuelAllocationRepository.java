package org.example.fuel_management_system.Repository;


import org.example.fuel_management_system.model.FuelAllocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FuelAllocationRepository extends JpaRepository<FuelAllocation,Integer> {

}
