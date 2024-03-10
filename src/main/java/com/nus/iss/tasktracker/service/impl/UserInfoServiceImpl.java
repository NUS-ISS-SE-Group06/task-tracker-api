package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.UserInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j

public class UserInfoServiceImpl implements UserInfoService {
    private  final UserInfoRepository userInfoRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository, UserMapper userMapper) {
        this.userInfoRepository = userInfoRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO UserLogin(UserDTO requestDTO){
        UserInfo userEntity = userInfoRepository.findByUsernameAndPasswordAndDeleteFlag (requestDTO.getUsername(),requestDTO.getPassword(),"FALSE");

        if (userEntity != null) {
            userEntity.setPassword("");
        }
        return userMapper.userEntityToUserDTO(userEntity);
    }

}
