package org.example.fuel_management_system.DTO;

public class StationWithStatusDTO  {
    private int id;
    private String dealerId;
    private String dealerName;
    private String status;

    public StationWithStatusDTO(String dealerId, String dealerName, String status) {
        this.dealerId = dealerId;
        this.dealerName = dealerName;
        this.status = status;
    }

    public StationWithStatusDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }
}
