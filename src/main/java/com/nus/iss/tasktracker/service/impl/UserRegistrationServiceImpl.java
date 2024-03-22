package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.GroupInfoRepository;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.GroupInfoService;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Objects;


@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private  final UserInfoRepository userInfoRepository;
    //private final GroupInfoRepository groupInfoRepository;

    private final UserMapper userMapper;

    // FIXME - UNCOMMENT THE BELOW CONSTRUCTOR CODE ONCE userInfoRepository CODE IS WRITTEN


@Autowired
    // FIXME - REMOVE THE BELOW DUMMY CONSTRUCTOR CODE ONCE userInfoRepository CODE IS WRITTEN
    public UserRegistrationServiceImpl(UserInfoRepository userInfoRepository, UserMapper userMapper, GroupInfoRepository groupInfoRepository) {
        this.userInfoRepository = userInfoRepository;
        this.userMapper = userMapper;
        //this.groupInfoRepository=groupInfoRepository;
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
        //userEntity = userMapper.mapChangePasswordRequestDTOToUser(userEntity, requestDTO);
        userEntity = userMapper.mapChangePasswordRequestDTOToUser(requestDTO);
        userEntity.setPassword(newPassword);
        userInfoRepository.save(userEntity);

    }

    @Override
    public UserDTO signUp(UserDTO requestDTO){

        if(Objects.equals(requestDTO.getName(), "")){
            throw new RuntimeException("Name - Please input value!");
        }

        if(Objects.equals(requestDTO.getEmail(), "")){
            throw new RuntimeException("Email - Please input value!");
        }

        if(Objects.equals(requestDTO.getUsername(), "")){
            throw new RuntimeException("Username - Please input value!");
        }

        if(Objects.equals(requestDTO.getPassword(), "")){
            throw new RuntimeException("Password - Please input value!");
        }

        if (requestDTO.getPassword().length() < 8 || !requestDTO.getPassword().matches(".*[a-zA-Z].*\\d.*")) {
            throw new RuntimeException("""
        Invalid Password. Password must be at least 8 characters long
        and contain a combination of letters, numbers, and special characters.
        """);
        }

        boolean isExists = userInfoRepository.existsByUsername(requestDTO.getUsername());
        if(isExists){
            throw new RuntimeException("Username not available!");
        }

        UserInfo userEntity=userMapper.mapChangePasswordRequestDTOToUser(requestDTO);
        userEntity.setCreatedDate(new Timestamp(System.currentTimeMillis()));
        userEntity.setDeleteFlag("FALSE");
        userEntity.setPasswordChangeMandatory("FALSE");
        UserDTO output= userMapper.userEntityToUserDTO(userInfoRepository.save(userEntity));
        output.setPassword(null);
        output.setOldPassword(null);
        output.setNewPassword(null);
        output.setAuthToken(null);
        output.setCreatedBy(null);

        return output;

    }

}
