package org.example.fuel_management_system.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class Registeredvehicles {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int vehicleId;

private String vehicleRegNo;
private String License_plate_no;
private String Vehicle_make;
private String Vehicle_model;
private String Vehicle_year;
private String Vehicle_color;
private String Vehicle_type;
private String Registration_status;
private String Fuel_type;
private String Registered_province;
private Date Resitration_expiry_date;
private String Owner_ID;


    public Registeredvehicles() {
    }

    public Registeredvehicles(int vehicleId, String vehicle_reg_no, String license_plate_no, String vehicle_make, String vehicle_model, String vehicle_year, String vehicle_color, String vehicle_type, String registration_status, String fuel_type, String registered_province, Date resitration_expiry_date, String owner_ID) {
        this.vehicleId = vehicleId;
        this.vehicleRegNo = vehicle_reg_no;
        License_plate_no = license_plate_no;
        Vehicle_make = vehicle_make;
        Vehicle_model = vehicle_model;
        Vehicle_year = vehicle_year;
        Vehicle_color = vehicle_color;
        Vehicle_type = vehicle_type;
        Registration_status = registration_status;
        Fuel_type = fuel_type;
        Registered_province = registered_province;
        Resitration_expiry_date = resitration_expiry_date;
        Owner_ID = owner_ID;
    }

    public int getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(int vehicleId) {
        this.vehicleId = vehicleId;
    }

    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicle_reg_no(String vehicle_reg_no) {
        this.vehicleRegNo = vehicle_reg_no;
    }

    public String getLicense_plate_no() {
        return License_plate_no;
    }

    public void setLicense_plate_no(String license_plate_no) {
        License_plate_no = license_plate_no;
    }

    public String getVehicle_make() {
        return Vehicle_make;
    }

    public void setVehicle_make(String vehicle_make) {
        Vehicle_make = vehicle_make;
    }

    public String getVehicle_model() {
        return Vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        Vehicle_model = vehicle_model;
    }

    public String getVehicle_year() {
        return Vehicle_year;
    }

    public void setVehicle_year(String vehicle_year) {
        Vehicle_year = vehicle_year;
    }

    public String getVehicle_color() {
        return Vehicle_color;
    }

    public void setVehicle_color(String vehicle_color) {
        Vehicle_color = vehicle_color;
    }

    public String getVehicle_type() {
        return Vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        Vehicle_type = vehicle_type;
    }

    public String getRegistration_status() {
        return Registration_status;
    }

    public void setRegistration_status(String registration_status) {
        Registration_status = registration_status;
    }

    public String getFuel_type() {
        return Fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        Fuel_type = fuel_type;
    }

    public String getRegistered_province() {
        return Registered_province;
    }

    public void setRegistered_province(String registered_province) {
        Registered_province = registered_province;
    }

    public Date getResitration_expiry_date() {
        return Resitration_expiry_date;
    }

    public void setResitration_expiry_date(Date resitration_expiry_date) {
        Resitration_expiry_date = resitration_expiry_date;
    }

    public String getOwner_ID() {
        return Owner_ID;
    }

    public void setOwner_ID(String owner_ID) {
        Owner_ID = owner_ID;
    }
}
