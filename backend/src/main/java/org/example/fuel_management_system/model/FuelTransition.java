package org.example.fuel_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @ManyToOne
    @JsonIgnore
    private VehicleVerification vehicleVerification;
    @ManyToOne
    @JsonIgnore
    private UserAccount userAccount;

    public FuelTransition(int id, String stationId, String fuelType, LocalDateTime transitionTime, Float fuelAmount, VehicleVerification vehicleVerification, UserAccount userAccount) {
        this.id = id;
        this.stationId = stationId;
        this.fuelType = fuelType;
        this.transitionTime = transitionTime;
        this.fuelAmount = fuelAmount;
        this.vehicleVerification = vehicleVerification;
        this.userAccount = userAccount;
    }

    public FuelTransition() {

    }

    public UserAccount getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(UserAccount userAccount) {
        this.userAccount = userAccount;
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
