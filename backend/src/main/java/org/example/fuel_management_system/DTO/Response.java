package org.example.fuel_management_system.DTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import org.example.fuel_management_system.enumpackage.Role;

import java.util.Date;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

    private String message;
    private int statusCode;
    private String token;
    private Role role;
    private Date expirationTime;
    private UserAccountDto userAccountDto;
    private VehiclesDto vehiclesDto;
    private StationDto stationDto;
    private FuelDto fuelDto;
    private FuelAllocationDto fuelAllocationDto;
    private List<UserAccountDto>userAccountDtoList;
    private List<VehiclesDto>vehiclesDtoList;
    private List<StationDto> stationDtosList;
    private List<FuelDto>fuelDtoList;


    public FuelAllocationDto getFuelAllocationDto() {
        return fuelAllocationDto;
    }

    public void setFuelAllocationDto(FuelAllocationDto fuelAllocationDto) {
        this.fuelAllocationDto = fuelAllocationDto;
    }


    public FuelDto getFuelDto() {
        return fuelDto;
    }

    public void setFuelDto(FuelDto fuelDto) {
        this.fuelDto = fuelDto;
    }

    public List<FuelDto> getFuelDtoList() {
        return fuelDtoList;
    }

    public void setFuelDtoList(List<FuelDto> fuelDtoList) {
        this.fuelDtoList = fuelDtoList;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Date getExpirationTime() {
        return expirationTime;
    }

    public void setExpirationTime(Date expirationTime) {
        this.expirationTime = expirationTime;
    }

    public UserAccountDto getUserAccountDto() {
        return userAccountDto;
    }

    public void setUserAccountDto(UserAccountDto userAccountDto) {
        this.userAccountDto = userAccountDto;
    }

    public VehiclesDto getVehiclesDto() {
        return vehiclesDto;
    }

    public void setVehiclesDto(VehiclesDto vehiclesDto) {
        this.vehiclesDto = vehiclesDto;
    }

    public StationDto getStationDto() {
        return stationDto;
    }

    public void setStationDto(StationDto stationDto) {
        this.stationDto = stationDto;
    }

    public List<UserAccountDto> getUserAccountDtoList() {
        return userAccountDtoList;
    }

    public void setUserAccountDtoList(List<UserAccountDto> userAccountDtoList) {
        this.userAccountDtoList = userAccountDtoList;
    }

    public List<VehiclesDto> getVehiclesDtoList() {
        return vehiclesDtoList;
    }

    public void setVehiclesDtoList(List<VehiclesDto> vehiclesDtoList) {
        this.vehiclesDtoList = vehiclesDtoList;
    }

    public List<StationDto> getStationDtosList() {
        return stationDtosList;
    }

    public void setStationDtosList(List<StationDto> stationDtosList) {
        this.stationDtosList = stationDtosList;
    }
}
