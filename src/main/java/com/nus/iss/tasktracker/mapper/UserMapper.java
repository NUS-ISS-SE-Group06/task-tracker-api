package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "role", target = "userRole")
    @Mapping(source= "userid", target="userId")
    UserDTO userEntityToUserDTO(UserInfo userEntity);

    @Mapping(source = "requestDTO.username", target = "username")
    @Mapping(source = "requestDTO.password", target = "password")
    UserInfo mapChangePasswordRequestDTOToUser(UserInfo user, UserDTO requestDTO);

}
