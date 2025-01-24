package org.example.fuel_management_system.DTO;

public class FuelAllocation {
    private String dealerId;
    private float WeeklyDieselAmount;
    private float WeeklyPetrolAmount;

    public String getDealerId() {
        return dealerId;
    }

    public void setDealerId(String dealerId) {
        this.dealerId = dealerId;
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
