package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private  final UserInfoRepository userInfoRepository;

    private final UserMapper userMapper;

    // FIXME - UNCOMMENT THE BELOW CONSTRUCTOR CODE ONCE userInfoRepository CODE IS WRITTEN


@Autowired
    // FIXME - REMOVE THE BELOW DUMMY CONSTRUCTOR CODE ONCE userInfoRepository CODE IS WRITTEN
    public UserRegistrationServiceImpl(UserInfoRepository userInfoRepository, UserMapper userMapper) {
        this.userInfoRepository = userInfoRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO getUserById(int userid) {
        log.info("getUserById called in UserRegistrationServiceImpl with id {}", userid);

        // FIXME - UNCOMMENT THE BELOW LINE ONCE userInfoRepository CODE IS WRITTEN
        // UserEntity userEntity = userInfoRepository.getUserInfoById(id);

        // FIXME - REMOVE THE BELOW DUMMY LINES OF CODE ONCE userInfoRepository CODE IS WRITTEN
        // DUMMY CODE START
        UserInfo userEntity = new UserInfo();
        userEntity.setUserid(userid);
       userEntity.setUserid(1);
       // userEntity.setUserRole("ADMIN");
        // DUMMY CODE END

        log.debug("User Entity {}",userEntity);
        UserDTO userDTO = userMapper.userEntityToUserDTO(userEntity);
        log.debug("User DTO {}",userDTO.toString());
        return userDTO;
    }
    @Override
    public void changePassword(UserDTO requestDTO){

        UserInfo userEntity = userInfoRepository.findByUsername(requestDTO.getUsername());
        System.out.println(requestDTO.getUsername());
        if(userEntity == null){
            throw new RuntimeException("Make sure the username is correct!");
        }
        if (!userEntity.getPassword().equals(requestDTO.getOldPassword())) {
            throw new RuntimeException("Old password does not match!");
        }

        String newPassword = requestDTO.getNewPassword();

        if (newPassword == null || newPassword.length() < 8) {
            throw new RuntimeException("Password must meet the min. length of 8!");
        }
        System.out.println(newPassword.matches(".*[a-zA-Z].*\\d.*"));
        if (!newPassword.matches(".*[a-zA-Z].*\\d.*")) {
            throw new RuntimeException("Password must contain alphanumeric characters!");
        }

        // Only update userEntity if all validations pass
        userEntity = userMapper.mapChangePasswordRequestDTOToUser(userEntity, requestDTO);
        userEntity.setPassword(newPassword);
        userInfoRepository.save(userEntity);

    }

}
