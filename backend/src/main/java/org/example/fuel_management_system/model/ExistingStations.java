package org.example.fuel_management_system.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExistingStations{
    @Id
    @Column(name = "dealer_id")
    private int dealerId;
    private String address;
    private String dealer_name;

    public int getDealerId() {
        return dealerId;
    }
}
