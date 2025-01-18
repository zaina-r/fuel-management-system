package org.example.fuel_management_system.utilities;

import org.example.fuel_management_system.DTO.UserAccountDto;
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
}
