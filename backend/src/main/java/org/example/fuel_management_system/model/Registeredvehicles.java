package org.example.fuel_management_system.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;



import jakarta.persistence.*;

@Entity
@Table(name = "registeredvehicles")
public class Registeredvehicles {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "vehicle_reg_no", nullable = false)
    private String vehicleRegNo;

    @Column(name = "fuel_type")
    private String fuelType;

    @Column(name = "licence_plate_no")
    private String licencePlateNo;

    @Column(name = "vehicle_make")
    private String vehicleMake;

    @Column(name = "vehicle_model")
    private String vehicleModel;

    @Column(name = "vehicle_type")
    private String vehicleType;

    @Column(name = "registered_province")
    private String registeredProvince;

    @Column(name = "registration_status")
    private String registrationStatus;

    @Column(name = "resitration_expiry_date")
    private String registrationExpiryDate;

    @Column(name = "vehicle_color")
    private String vehicleColor;

    @Column(name = "vehicle_year")
    private String vehicleYear;

    public Registeredvehicles(int id, String vehicleRegNo, String fuelType, String licencePlateNo, String vehicleMake, String vehicleModel, String vehicleType, String registeredProvince, String registrationStatus, String registrationExpiryDate, String vehicleColor, String vehicleYear) {
        this.id = id;
        this.vehicleRegNo = vehicleRegNo;
        this.fuelType = fuelType;
        this.licencePlateNo = licencePlateNo;
        this.vehicleMake = vehicleMake;
        this.vehicleModel = vehicleModel;
        this.vehicleType = vehicleType;
        this.registeredProvince = registeredProvince;
        this.registrationStatus = registrationStatus;
        this.registrationExpiryDate = registrationExpiryDate;
        this.vehicleColor = vehicleColor;
        this.vehicleYear = vehicleYear;
    }

    public Registeredvehicles() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getLicencePlateNo() {
        return licencePlateNo;
    }

    public void setLicencePlateNo(String licencePlateNo) {
        this.licencePlateNo = licencePlateNo;
    }

    public String getVehicleMake() {
        return vehicleMake;
    }

    public void setVehicleMake(String vehicleMake) {
        this.vehicleMake = vehicleMake;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    public String getRegisteredProvince() {
        return registeredProvince;
    }

    public void setRegisteredProvince(String registeredProvince) {
        this.registeredProvince = registeredProvince;
    }

    public String getRegistrationStatus() {
        return registrationStatus;
    }

    public void setRegistrationStatus(String registrationStatus) {
        this.registrationStatus = registrationStatus;
    }

    public String getRegistrationExpiryDate() {
        return registrationExpiryDate;
    }

    public void setRegistrationExpiryDate(String registrationExpiryDate) {
        this.registrationExpiryDate = registrationExpiryDate;
    }

    public String getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(String vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public String getVehicleYear() {
        return vehicleYear;
    }

    public void setVehicleYear(String vehicleYear) {
        this.vehicleYear = vehicleYear;
    }
}





