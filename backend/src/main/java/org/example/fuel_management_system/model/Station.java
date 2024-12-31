package org.example.fuel_management_system.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "station_registration")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;

    @Column(name = "station_address", nullable = false)
    private String stationAddress;
    @Column(name = "dealer_name", nullable = false)
    private String dealerName;
    @Lob
    private byte[] verificationDocument;
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate;
    @Column //todo: include that this is a unique value
    private String loginCode;

    public String getLoginCode() {
        return loginCode;
    }

    public void setLoginCode(String loginCode) {
        this.loginCode = loginCode;
    }

    public byte[] getVerificationDocument() {
        return verificationDocument;
    }

    public void setVerificationDocument(byte[] verificationDocument) {
        this.verificationDocument = verificationDocument;
    }

    @ManyToMany
    @JoinTable(
            name = "station_fuel",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_id")
    )
    private List<Fuel> fuel = new ArrayList<>();

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
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

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDate registrationDate) {
        this.registrationDate = registrationDate;
    }

    public List<Fuel> getFuel() {
        return fuel;
    }

    public void setFuel(List<Fuel> fuel) {
        this.fuel = fuel;
    }
}
