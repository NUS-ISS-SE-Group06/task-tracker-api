package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.UserEntity;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private UserInfoRepository userInfoRepository;

    private UserMapper userMapper;

    // FIXME - UNCOMMENT THE BELOW CONSTRUCTOR CODE ONCE userInfoRepository CODE IS WRITTEN
    // @Autowired
//    public UserRegistrationServiceImpl(UserInfoRepository userInfoRepository, UserMapper userMapper) {
//        this.userInfoRepository = userInfoRepository;
//        this.userMapper = userMapper;
//    }

    // FIXME - REMOVE THE BELOW DUMMY CONSTRUCTOR CODE ONCE userInfoRepository CODE IS WRITTEN
    public UserRegistrationServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(int id) {
        log.info("getUserById called in UserRegistrationServiceImpl with id {}", id);

        // FIXME - UNCOMMENT THE BELOW LINE ONCE userInfoRepository CODE IS WRITTEN
        // UserEntity userEntity = userInfoRepository.getUserInfoById(id);

        // FIXME - REMOVE THE BELOW DUMMY LINES OF CODE ONCE userInfoRepository CODE IS WRITTEN
        // DUMMY CODE START
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUserName("Balaji-Britta-YewHoon-Yudi-William");
        userEntity.setUserRole("ADMIN");
        // DUMMY CODE END

        log.debug("User Entity {}",userEntity.toString());
        UserDTO userDTO = userMapper.userEntityToUserDTO(userEntity);
        log.debug("User DTO {}",userDTO.toString());
        return userDTO;
    }
}
