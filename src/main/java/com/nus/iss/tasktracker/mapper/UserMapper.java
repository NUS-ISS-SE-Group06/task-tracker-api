package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.model.UserInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userEntityToUserDTO(UserInfo userEntity);

    @Mapping(source = "requestDTO.username", target = "username")
    UserInfo mapChangePasswordRequestDTOToUser(UserInfo user, UserDTO requestDTO);

}
