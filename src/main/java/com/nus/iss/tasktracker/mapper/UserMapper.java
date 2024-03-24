package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "role", target = "userRole")
    @Mapping(target = "oldPassword", ignore = true)
    @Mapping(target = "newPassword", ignore = true)
    @Mapping(target = "authToken", ignore = true)
    UserDTO userEntityToUserDTO(UserInfo userEntity);

    @Mapping(source = "userRole", target = "role")
    UserInfo mapChangePasswordRequestDTOToUser(UserDTO requestDTO);

    @Mapping(source = "userRole", target = "role")
    UserInfo userDTOToUserInfo(UserDTO userDTO);

}
