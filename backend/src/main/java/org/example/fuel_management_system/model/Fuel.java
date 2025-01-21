package org.example.fuel_management_system.model;
import jakarta.persistence.*;




@Entity
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fuelId;

    private String fuelType;

    private Float availableQuantity;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;


    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public Float getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Float availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
