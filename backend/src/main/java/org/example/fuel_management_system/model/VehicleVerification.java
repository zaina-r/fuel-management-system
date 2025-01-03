package org.example.fuel_management_system.model;

import jakarta.persistence.*;

@Entity
public class VehicleVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vehicleId;

    private String vehicleRegNo;
    private String license_plate_no;
    private String vehicle_make;
    private String vehicle_model;
    private String vehicle_type;
    private String fuel_type;
    private float maximumFuelCapacity;
    private float availableFuelCapacity;
    private String qrCode;

    public VehicleVerification(int vehicleId, String vehicleRegNo, String license_plate_no, String vehicle_make, String vehicle_model, String vehicle_type, String fuel_type, float maximumFuelCapacity, float availableFuelCapacity , String qrCode) {
        this.vehicleId = vehicleId;
        this.vehicleRegNo = vehicleRegNo;
        this.license_plate_no = license_plate_no;
        this.vehicle_make = vehicle_make;
        this.vehicle_model = vehicle_model;
        this.vehicle_type = vehicle_type;
        this.fuel_type = fuel_type;
        this.maximumFuelCapacity = maximumFuelCapacity;
        this.availableFuelCapacity = availableFuelCapacity;
        this.qrCode = qrCode;

    }

    public VehicleVerification() {

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

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public String getLicense_plate_no() {
        return license_plate_no;
    }

    public void setLicense_plate_no(String license_plate_no) {
        this.license_plate_no = license_plate_no;
    }

    public String getVehicle_make() {
        return vehicle_make;
    }

    public void setVehicle_make(String vehicle_make) {
        this.vehicle_make = vehicle_make;
    }

    public String getVehicle_model() {
        return vehicle_model;
    }

    public void setVehicle_model(String vehicle_model) {
        this.vehicle_model = vehicle_model;
    }

    public String getVehicle_type() {
        return vehicle_type;
    }

    public void setVehicle_type(String vehicle_type) {
        this.vehicle_type = vehicle_type;
    }

    public String getFuel_type() {
        return fuel_type;
    }

    public void setFuel_type(String fuel_type) {
        this.fuel_type = fuel_type;
    }

    public float getMaximumFuelCapacity() {
        return maximumFuelCapacity;
    }

    public void setMaximumFuelCapacity(float maximumFuelCapacity) {
        this.maximumFuelCapacity = maximumFuelCapacity;
    }

    public float getAvailableFuelCapacity() {
        return availableFuelCapacity;
    }

    public void setAvailableFuelCapacity(float availableFuelCapacity) {
        this.availableFuelCapacity = availableFuelCapacity;
    }

    public String getQrCode() {
        return qrCode;
    }

    public void setQrCode(String qrCode) {
        this.qrCode = qrCode;
    }
}