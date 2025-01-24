package org.example.fuel_management_system.utilities;

import org.example.fuel_management_system.DTO.FuelDto;
import org.example.fuel_management_system.DTO.StationDto;
import org.example.fuel_management_system.DTO.UserAccountDto;
import org.example.fuel_management_system.DTO.VehiclesDto;
import org.example.fuel_management_system.model.Fuel;
import org.example.fuel_management_system.model.Station;
import org.example.fuel_management_system.model.UserAccount;
import org.example.fuel_management_system.model.VehicleVerification;

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

       
       fuelDto.setStation(fuel.getStation());

       fuelDto.setAvailablePetrolQuantity(fuel.getAvailablePetrolQuantity());
       fuelDto.setAvailableDiselQuantity(fuel.getAvailableDiselQuantity());

       fuelDto.setFuelId(fuel.getFuelId());

        return fuelDto;
    }

    public static List<FuelDto> mapFuelListEntityToFuelListDTO(List<Fuel> fuels) {
        return fuels.stream().map(MapUtils::mapFuelEntityToFuelDTO).collect(Collectors.toList());
    }
    public static VehiclesDto mapVehicleEntityToVehicleDTO(VehicleVerification vehicleVerification) {


        VehiclesDto vehiclesDto = new VehiclesDto();
        vehiclesDto.setVehicleRegNo(vehicleVerification.getVehicleRegNo());
        vehiclesDto.setVehicle_type(vehicleVerification.getVehicle_type());
        vehiclesDto.setMaximumFuelCapacity(vehicleVerification.getMaximumFuelCapacity());
        vehiclesDto.setAvailableFuelCapacity(vehicleVerification.getAvailableFuelCapacity());
        vehiclesDto.setLicense_plate_no(vehicleVerification.getLicense_plate_no());
        vehiclesDto.setQrCode(vehicleVerification.getQrCode());
        vehiclesDto.setVehicleId(vehicleVerification.getVehicleId());
        vehiclesDto.setFuel_type(vehicleVerification.getFuel_type());
        vehiclesDto.setUserAccount(vehicleVerification.getUserAccount());

        return vehiclesDto;
    }
    public static List<VehiclesDto> mapVehicleListEntityToVehicleDTOList(List<VehicleVerification> vehicleVerifications) {
        return vehicleVerifications.stream().map(MapUtils::mapVehicleEntityToVehicleDTO).collect(Collectors.toList());
    }
}
