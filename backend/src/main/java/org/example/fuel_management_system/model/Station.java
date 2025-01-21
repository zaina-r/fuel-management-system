package org.example.fuel_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "station_registration")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String stationId;

    @Column(name = "station_address", nullable = false)
    private String stationAddress;
    @Column(name = "dealer_name", nullable = false)
    private String dealerName;
    private String licenseNumber;

    private LocalDate registrationDate;
   @Column
    private String loginCode;


    @OneToMany(mappedBy = "station", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
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
