package org.example.fuel_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;

    private String stationAddress;

    private LocalDate registrationDate;

    @JsonIgnore
    @OneToOne
    private UserAccount petrolOwner;

    @ManyToMany
    @JoinTable(
            name = "station_fuel",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_id")
    )
    private List<Fuel> fuel = new ArrayList<>();
}
