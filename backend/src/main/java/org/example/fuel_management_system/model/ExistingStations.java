package org.example.fuel_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExistingStations{
    @Id
    @Column(name = "dealer_id")
    private String dealerId;
    private String address;
    private String dealer_name;
    private String licenseNumber;

    public String getLicense_number() {
        return licenseNumber;
    }

    public void setLicense_number(String license_number) {
        this.licenseNumber = license_number;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDealer_name() {
        return dealer_name;
    }

    public void setDealer_name(String dealer_name) {
        this.dealer_name = dealer_name;
    }
}
