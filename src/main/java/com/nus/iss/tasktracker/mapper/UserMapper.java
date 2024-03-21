package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {


    @Mapping(source = "role", target = "userRole")
    @Mapping(source = "userid", target = "userId")
    @Mapping(source = "passwordChangeMandatory", target = "passwordChangeMandatory")
    @Mapping(target = "oldPassword", ignore = true)
    @Mapping(target = "newPassword", ignore = true)
    @Mapping(target = "authToken", ignore = true)
    UserDTO userEntityToUserDTO(UserInfo userEntity);


//    @Mapping(source = "requestDTO.groupId", target = "groupId")
//    @Mapping(source = "requestDTO.email", target = "email")
//    @Mapping(source = "requestDTO.name", target = "name")
//    @Mapping(source = "requestDTO.username", target = "username")
//    @Mapping(source = "requestDTO.password", target = "password")
//    @Mapping(source = "requestDTO.groupDescription", target = "groupDescription")
//
//    UserInfo mapChangePasswordRequestDTOToUser(UserInfo user, UserDTO requestDTO);


    @Mapping(source = "userRole", target = "role")
    UserInfo mapChangePasswordRequestDTOToUser(UserDTO requestDTO);



}
