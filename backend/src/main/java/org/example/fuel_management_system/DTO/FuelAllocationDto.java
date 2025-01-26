package org.example.fuel_management_system.DTO;

import org.example.fuel_management_system.model.Station;

public  class FuelAllocationDto {
    private int id;
    private float WeeklyDieselAmount;
    private float WeeklyPetrolAmount;
    private Station station;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public float getWeeklyDieselAmount() {
        return WeeklyDieselAmount;
    }

    public void setWeeklyDieselAmount(float weeklyDieselAmount) {
        this.WeeklyDieselAmount = weeklyDieselAmount;
    }

    public float getWeeklyPetrolAmount() {
        return WeeklyPetrolAmount;
    }

    public void setWeeklyPetrolAmount(float weeklyPetrolAmount) {
        this.WeeklyPetrolAmount = weeklyPetrolAmount;
    }
}
