package org.example.fuel_management_system.DTO;

import org.example.fuel_management_system.model.Station;

public class FuelDto {


    private int fuelId;
    private Float availableDieselQuantity;
    private Float availablePetrolQuantity;

    private Station station;

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
