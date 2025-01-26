package org.example.fuel_management_system.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
public class FuelTransition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String stationId;
    private String fuelType;
    private Float fuelAmount;
    private LocalDateTime transitionTime;
    @OneToOne
    private VehicleVerification vehicleVerification;

    public FuelTransition(String stationId, String fuelType, Float fuelAmount, LocalDateTime transitionTime, VehicleVerification vehicleVerification) {
        this.stationId = stationId;
        this.fuelType = fuelType;
        this.fuelAmount = fuelAmount;
        this.transitionTime = transitionTime;
        this.vehicleVerification = vehicleVerification;
    }

    public FuelTransition() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Float getFuelAmount() {
        return fuelAmount;
    }

    public void setFuelAmount(Float fuelAmount) {
        this.fuelAmount = fuelAmount;
    }

    public LocalDateTime getTransitionTime() {
        return transitionTime;
    }

    public void setTransitionTime(LocalDateTime transitionTime) {
        this.transitionTime = transitionTime;
    }

    public VehicleVerification getVehicleVerification() {
        return vehicleVerification;
    }

    public void setVehicleVerification(VehicleVerification vehicleVerification) {
        this.vehicleVerification = vehicleVerification;
    }
}
