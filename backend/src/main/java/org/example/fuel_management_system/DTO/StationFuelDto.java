package org.example.fuel_management_system.DTO;

import java.time.LocalDate;

public class StationFuelDto {
    private String stationId;
    private String stationAddress;
    private String dealerName;
    private String licenseNumber;
    private String registrationDate;
    private Float availableDiselQuantity;
    private Float availablePetrolQuantity;

    public StationFuelDto() {

    }

    public StationFuelDto(String stationId, String stationAddress, String dealerName, String licenseNumber, LocalDate registrationDate, Float availableDiselQuantity, Float availablePetrolQuantity) {
        this.stationId = stationId;
        this.stationAddress = stationAddress;
        this.dealerName = dealerName;
        this.licenseNumber = licenseNumber;
        this.registrationDate = String.valueOf(registrationDate);
        this.availableDiselQuantity = availableDiselQuantity;
        this.availablePetrolQuantity = availablePetrolQuantity;
    }

    public String getStationId() {
        return stationId;
    }

    public void setStationId(String stationId) {
        this.stationId = stationId;
    }

    public String getStationAddress() {
        return stationAddress;
    }

    public void setStationAddress(String stationAddress) {
        this.stationAddress = stationAddress;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Float getAvailableDiselQuantity() {
        return availableDiselQuantity;
    }

    public void setAvailableDiselQuantity(Float availableDiselQuantity) {
        this.availableDiselQuantity = availableDiselQuantity;
    }

    public Float getAvailablePetrolQuantity() {
        return availablePetrolQuantity;
    }

    public void setAvailablePetrolQuantity(Float availablePetrolQuantity) {
        this.availablePetrolQuantity = availablePetrolQuantity;
    }
}
