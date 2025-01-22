package org.example.fuel_management_system.utilities;

import org.example.fuel_management_system.DTO.FuelDto;
import org.example.fuel_management_system.DTO.StationDto;
import org.example.fuel_management_system.DTO.UserAccountDto;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.model.UserAccount;

import java.util.List;
import java.util.stream.Collectors;

public class MapUtils {

    public static UserAccountDto mapUserEntityToUserDTO(UserAccount userAccount) {
        UserAccountDto userDTO = new UserAccountDto();

        userDTO.setUserId(userAccount.getUserId());
        userDTO.setFirstname(userAccount.getFirstname());
       userDTO.setLastname(userAccount.getLastname());
       userDTO.setUsername(userAccount.getUsername());
       userDTO.setLicenseNumber(userAccount.getLicenseNumber());
       userDTO.setNIC(userAccount.getNIC());
       userDTO.setTelno(userAccount.getTelno());
       userDTO.setRole(userAccount.getRole());
        return userDTO;
    }
    public static List<UserAccountDto> mapUserListEntityToUserListDTO(List<UserAccount> userList) {
        return userList.stream().map(MapUtils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }
    public static StationDto mapStationEntityToStationDTO(Station station) {
        StationDto stationDto = new StationDto();

        stationDto.setId(station.getId());
         stationDto.setStationId(station.getStationId());
         stationDto.setStationAddress(stationDto.getStationAddress());
         stationDto.setLoginCode(station.getLoginCode());
         stationDto.setLicenseNumber(station.getLicenseNumber());
         stationDto.setFuel(station.getFuel());
         stationDto.setRegistrationDate(station.getRegistrationDate());
         stationDto.setDealerName(station.getDealerName());

        return stationDto;
    }
    public static List<StationDto> mapUserListEntityToStationListDTO(List<Station> stations) {
        return stations.stream().map(MapUtils::mapStationEntityToStationDTO).collect(Collectors.toList());
    }

    public static FuelDto mapFuelEntityToFuelDTO(Fuel fuel) {
        FuelDto fuelDto = new FuelDto();

       fuelDto.setFuelType(fuel.getFuelType());
       fuelDto.setStation(fuelDto.getStation());
       fuelDto.setAvailableQuantity(fuelDto.getAvailableQuantity());
       fuelDto.setFuelId(fuelDto.getFuelId());

        return fuelDto;
    }

    public static List<FuelDto> mapFuelListEntityToFuelListDTO(List<Fuel> fuels) {
        return fuels.stream().map(MapUtils::mapFuelEntityToFuelDTO).collect(Collectors.toList());
    }
}
