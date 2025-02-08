package org.example.fuel_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StationWithRegistrationStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String stationId;
    private String dealerName;
    private String status;

    public StationWithRegistrationStatus( String stationId, String dealerName, String status) {
        this.stationId = stationId;
        this.dealerName = dealerName;
        this.status = status;
    }

    public StationWithRegistrationStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDealerId() {
        return stationId;
    }

    public void setDealerId(String dealerId) {
        this.stationId = dealerId;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
