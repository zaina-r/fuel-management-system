package org.example.fuel_management_system.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.example.fuel_management_system.model.Fuel;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class StationDto {

    private int id;
    private String stationId;

    private String stationAddress;

    private String dealerName;
    private String licenseNumber;

    private LocalDate registrationDate;

    private String loginCode;

    private List<Fuel> fuel = new ArrayList<>();

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

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public List<Fuel> getFuel() {
        return fuel;
    }

    public void setFuel(List<Fuel> fuel) {
        this.fuel = fuel;
    }
}
