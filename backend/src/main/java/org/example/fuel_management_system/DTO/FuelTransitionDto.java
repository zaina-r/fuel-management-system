package org.example.fuel_management_system.DTO;

import jakarta.persistence.OneToOne;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.model.VehicleVerification;

import java.time.LocalDateTime;

public class FuelTransitionDto {

    private int id;
    private String stationId;
    private String fuelType;
    private Float fuelAmount;
    private LocalDateTime transitionTime;
    private VehicleVerification vehicleVerification;
    private UserAccount userAccount;

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
