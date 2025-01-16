package org.example.fuel_management_system.model;
import jakarta.persistence.*;




@Entity
public class Fuel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fuelId;

    private String petrol;
    private String disel;

    private Float availablePetrolQuantiy;
    private Float availableDiselQuantiy;

    @ManyToOne
    @JoinColumn(name = "station_id")
    private Station station;


    public int getFuelId() {
        return fuelId;
    }

    public void setFuelId(int fuelId) {
        this.fuelId = fuelId;
    }


    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public String getPetrol() {
        return petrol;
    }

    public void setPetrol(String petrol) {
        this.petrol = petrol;
    }

    public String getDisel() {
        return disel;
    }

    public void setDisel(String disel) {
        this.disel = disel;
    }

    public Float getAvailablePetrolQuantiy() {
        return availablePetrolQuantiy;
    }

    public void setAvailablePetrolQuantiy(Float availablePetrolQuantiy) {
        this.availablePetrolQuantiy = availablePetrolQuantiy;
    }

    public Float getAvailableDiselQuantiy() {
        return availableDiselQuantiy;
    }

    public void setAvailableDiselQuantiy(Float availableDiselQuantiy) {
        this.availableDiselQuantiy = availableDiselQuantiy;
    }
}
