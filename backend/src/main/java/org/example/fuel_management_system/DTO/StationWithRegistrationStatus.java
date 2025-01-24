package org.example.fuel_management_system.DTO;

public class StationWithRegistrationStatus {

    private String dealerId;
    private String dealerName;
    private String status;

    public StationWithRegistrationStatus(String dealerId, String dealerName, String status) {
        this.dealerId = dealerId;
        this.dealerName = dealerName;
        this.status = status;
    }
}
