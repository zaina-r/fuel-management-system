package org.example.fuel_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FuelPrice {

    @Id
    private int id;

    private String fId;
    private String fuelName;
    private double price;

    public FuelPrice(int id, String fId, String fuelName, double price) {
        this.id = id;
        this.fId = fId;
        this.fuelName = fuelName;
        this.price = price;
    }

    public FuelPrice() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getfId() {
        return fId;
    }

    public void setfId(String fId) {
        this.fId = fId;
    }

    public String getFuelName() {
        return fuelName;
    }

    public void setFuelName(String fuelName) {
        this.fuelName = fuelName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
