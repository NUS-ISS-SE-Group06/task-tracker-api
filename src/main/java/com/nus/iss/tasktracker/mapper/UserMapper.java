package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.model.UserEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO userEntityToUserDTO(UserEntity userEntity);
}
