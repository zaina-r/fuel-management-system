package org.example.fuel_management_system.utilities;

import org.example.fuel_management_system.DTO.*;
import org.example.fuel_management_system.model.*;

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
       userDTO.setImageName(userAccount.getImageName());
       userDTO.setImageData(userAccount.getImageData());
       userDTO.setImageType(userAccount.getImageType());
        return userDTO;
    }
    public static List<UserAccountDto> mapUserListEntityToUserListDTO(List<UserAccount> userList) {
        return userList.stream().map(MapUtils::mapUserEntityToUserDTO).collect(Collectors.toList());
    }
    public static StationDto mapStationEntityToStationDTO(Station station) {
        StationDto stationDto = new StationDto();
        //station details

        stationDto.setId(station.getId());
         stationDto.setStationId(station.getStationId());
         stationDto.setStationAddress(station.getStationAddress());
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
       fuelDto.setAvailableDieselQuantity(fuel.getAvailableDieselQuantity());
        fuelDto.setInitialDieselAllocation(fuel.getInitialDieselAllocation());
        fuelDto.setInitialPetrolAllocation(fuel.getInitialPetrolAllocation());
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
    public static FuelAllocationDto mapFuelAllocationEntityToFuelAllocationDTO(FuelAllocation fuelAllocation){
        FuelAllocationDto fuelAllocationDto=new FuelAllocationDto();
        fuelAllocationDto.setId(fuelAllocation.getId());
        fuelAllocationDto.setStation(fuelAllocation.getStation());
        fuelAllocationDto.setWeeklyDieselAmount(fuelAllocation.getWeeklyDieselAmount());
        fuelAllocationDto.setWeeklyPetrolAmount(fuelAllocation.getWeeklyPetrolAmount());
        fuelAllocationDto.setStation(fuelAllocation.getStation());
        return fuelAllocationDto;
    }

    public static StationWithStatusDTO mapStationWithStatusEntityToStationWithStatusDTO(StationWithRegistrationStatus stationWithRegistrationStatus){
        StationWithStatusDTO stationWithStatusDTO=new StationWithStatusDTO();
        stationWithStatusDTO.setId(stationWithRegistrationStatus.getId());
        stationWithStatusDTO.setStatus(stationWithRegistrationStatus.getStatus());
        stationWithStatusDTO.setDealerName(stationWithRegistrationStatus.getDealerName());
        stationWithStatusDTO.setDealerId(stationWithRegistrationStatus.getDealerId());
        return stationWithStatusDTO;
    }

    public static List<StationWithStatusDTO> mapListStationWithStatusDTOToListStationWithStatus(List<StationWithRegistrationStatus>stationWithRegistrationStatusList){

        return stationWithRegistrationStatusList.stream().map(MapUtils::mapStationWithStatusEntityToStationWithStatusDTO).collect(Collectors.toList());

    }

    public static FuelTransitionDto mapFuelTransitionEntityToFuelTransitionDTO(FuelTransition fuelTransition){
        FuelTransitionDto fuelTransitionDto=new FuelTransitionDto();
        fuelTransitionDto.setId(fuelTransition.getId());
        fuelTransitionDto.setFuelAmount(fuelTransition.getFuelAmount());
        fuelTransitionDto.setFuelType(fuelTransition.getFuelType());
        fuelTransitionDto.setTransitionTime(fuelTransition.getTransitionTime());
        fuelTransitionDto.setStationId(fuelTransition.getStationId());
        fuelTransitionDto.setVehicleVerification(fuelTransition.getVehicleVerification());
        fuelTransitionDto.setUserAccount(fuelTransition.getUserAccount());
        return fuelTransitionDto;
    }

    public static List<FuelTransitionDto> mapListFuelTransitionDtoToFuelTransition(List<FuelTransition>FuelTransition){

        return FuelTransition.stream().map(MapUtils::mapFuelTransitionEntityToFuelTransitionDTO).collect(Collectors.toList());

    }
    public static FuelPriceDto mapFuelPriceEntityToFuelPriceDTO(FuelPrice fuelPrice){
        FuelPriceDto fuelPriceDto=new FuelPriceDto();
        fuelPriceDto.setId(fuelPrice.getId());
        fuelPriceDto.setfId(fuelPrice.getfId());
        fuelPriceDto.setFuelName(fuelPrice.getFuelName());
        fuelPriceDto.setPrice(fuelPrice.getPrice());

        return fuelPriceDto;
    }
    public static List<FuelPriceDto> mapListFuelPriceEntityToFuelPriceDTO(List<FuelPrice> FuelTransition){

        return FuelTransition.stream().map(MapUtils::mapFuelPriceEntityToFuelPriceDTO).collect(Collectors.toList());

    }
    public static VehicleDto mapVehicleEntityToVehicleDto(Vehicle vehicle){
        VehicleDto vehicleDto=new VehicleDto();
        vehicleDto.setId(vehicle.getId());
        vehicleDto.setVehicleType(vehicle.getVehicleType());
        vehicleDto.setFuelCapacity(vehicle.getFuelCapacity());

        return vehicleDto;
    }
    public static List<VehicleDto> mapListVehicleEntityToVehicleDto(List<Vehicle> vehicles){
        return vehicles.stream().map(MapUtils::mapVehicleEntityToVehicleDto).collect(Collectors.toList());
    }


}
