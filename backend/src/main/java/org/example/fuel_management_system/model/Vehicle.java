package org.example.fuel_management_system.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "vehicles")
@Data
public class Vehicle {
    @Id
    private String Vehicle_reg_no;
    private String Licence_plate_no;

    public Vehicle(String vehicle_reg_no, String licence_plate_no) {
        Vehicle_reg_no = vehicle_reg_no;
        Licence_plate_no = licence_plate_no;
    }

    public Vehicle() {
    }
}
