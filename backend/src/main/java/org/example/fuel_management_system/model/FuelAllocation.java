package org.example.fuel_management_system.model;


import jakarta.persistence.*;

@Entity
public  class FuelAllocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private float WeeklyDieselAmount;
    private float WeeklyPetrolAmount;
    @OneToOne
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
