package org.example.fuel_management_system.model;
import jakarta.persistence.*;




@Entity
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fuelId;

    private Float availableDieselQuantity;
    private float weeklyDieselAllocation;

    private Float availablePetrolQuantity;
    private float weeklyPetrolAllocation;

    @OneToOne
    @JoinColumn(name = "station_id")
    private Station station;

    public float getWeeklyDieselAllocation() {
        return weeklyDieselAllocation;
    }

    public void setWeeklyDieselAllocation(float weeklyDieselAllocation) {
        this.weeklyDieselAllocation = weeklyDieselAllocation;
    }

    public float getWeeklyPetrolAllocation() {
        return weeklyPetrolAllocation;
    }

    public void setWeeklyPetrolAllocation(float weeklyPetrolAllocation) {
        this.weeklyPetrolAllocation = weeklyPetrolAllocation;
    }

    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }

    public Float getAvailableDieselQuantity() {
        return availableDieselQuantity;
    }

    public void setAvailableDieselQuantity(Float availableDieselQuantity) {
        this.availableDieselQuantity = availableDieselQuantity;
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
