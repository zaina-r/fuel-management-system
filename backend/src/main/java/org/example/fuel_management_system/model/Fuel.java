package org.example.fuel_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fuelId;

    private String fuelType;

    private Float availableQuantity;

    @JsonIgnore
    @ManyToMany(mappedBy = "fuel")
    private List<Station> station = new ArrayList<>();
}
