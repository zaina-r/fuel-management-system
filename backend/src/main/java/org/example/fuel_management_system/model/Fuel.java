package org.example.fuel_management_system.model;
import jakarta.persistence.*;




@Entity
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fuelId;

    private Float availableDiselQuantity;

    private Float availablePetrolQuantity;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;


    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }

    public Float getAvailableDiselQuantity() {
        return availableDiselQuantity;
    }

    public void setAvailableDiselQuantity(Float availableDiselQuantity) {
        this.availableDiselQuantity = availableDiselQuantity;
    }

    public Float getAvailablePetrolQuantity() {
        return availablePetrolQuantity;
    }

    public void setAvailablePetrolQuantity(Float availablePetrolQuantity) {
        this.availablePetrolQuantity = availablePetrolQuantity;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
}
