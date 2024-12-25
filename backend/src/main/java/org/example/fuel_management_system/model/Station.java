package org.example.fuel_management_system.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "station_registration")
public class Station {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stationId;

    @Column(name = "station_address", length = 255, nullable = false)
    private String stationAddress;
    @Column(name = "dealer_name", length = 255, nullable = false)
    private String dealerName;
    @Temporal(TemporalType.DATE)
    private LocalDate registrationDate;


    @ManyToMany
    @JoinTable(
            name = "station_fuel",
            joinColumns = @JoinColumn(name = "station_id"),
            inverseJoinColumns = @JoinColumn(name = "fuel_id")
    )
    private List<Fuel> fuel = new ArrayList<>();

}
