package org.example.fuel_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ExistingStations{
    @Id
    private int dealer_id;
    private String address;
    private String dealer_name;

    public int getDealer_id() {
        return dealer_id;
    }
}
