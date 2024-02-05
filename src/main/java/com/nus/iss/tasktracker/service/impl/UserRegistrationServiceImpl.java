package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dao.UserRegistrationDAO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.UserEntity;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private UserRegistrationDAO userRegistrationDAO;
    private UserMapper userMapper;

    @Autowired
    public UserRegistrationServiceImpl(UserRegistrationDAO userRegistrationDAO, UserMapper userMapper) {
        this.userRegistrationDAO = userRegistrationDAO;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(int id) {
        log.info("getUserById called in UserRegistrationServiceImpl with id {}", id);
        UserEntity userEntity = userRegistrationDAO.getUserInfoById(id);
        log.debug("User Entity {}",userEntity.toString());
        UserDTO userDTO = userMapper.userEntityToUserDTO(userEntity);
        log.debug("User DTO {}",userDTO.toString());
        return userDTO;
    }
}
