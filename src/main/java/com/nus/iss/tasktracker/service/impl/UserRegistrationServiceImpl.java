package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.UserMapper;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.GroupInfoService;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Objects;


@Service
@Slf4j
public class UserRegistrationServiceImpl implements UserRegistrationService {

    private  final UserInfoRepository userInfoRepository;
    private final GroupInfoService groupInfoService;
    private final UserMapper userMapper;

@Autowired

    public UserRegistrationServiceImpl(UserInfoRepository userInfoRepository, UserMapper userMapper, GroupInfoService groupInfoService) {
        this.userInfoRepository = userInfoRepository;
        this.groupInfoService = groupInfoService;
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
        userEntity.setUserId(userid);
       userEntity.setUserId(1);
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

        // FIXME - DO NULL CHECKS ALSO PLEASE
        if(Objects.equals(requestDTO.getName(), "")){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Name"));
        }

        if(Objects.equals(requestDTO.getEmail(), "")){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Email"));
        }

        if(Objects.equals(requestDTO.getGroupName(), "")){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Group Name"));
        }

        if (requestDTO.getGroupName().length() < 6 || !requestDTO.getGroupName().matches("^(?! )[0-9A-Za-z](?!.* $)[0-9A-Za-z\\s]{0,18}(?<! )$")) {
            throw new RuntimeException(TaskTrackerConstant.SIGNUP_INVALID_GROUP_NAME);
        }

        if(Objects.equals(requestDTO.getUsername(), "")){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Username"));
        }

        if(Objects.equals(requestDTO.getPassword(), "")){
            throw new RuntimeException(String.format(TaskTrackerConstant.SIGNUP_INVALID_INPUT, "Password"));
        }

        if (requestDTO.getPassword().length() < 8 || !requestDTO.getPassword().matches(".*[a-zA-Z].*\\d.*")) {
            throw new RuntimeException(TaskTrackerConstant.SIGNUP_INVALID_INPUT_PASSWORD);
        }

        boolean isExists = userInfoRepository.existsByUsername(requestDTO.getUsername());
        if(isExists){
            throw new RuntimeException(TaskTrackerConstant.SIGNUP_INVALID_INPUT_USERNAME_UNAVAILABLE);
        }

        // STORING GROUP FIRST TO GET GROUP ID
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName(requestDTO.getGroupName());
        groupDTO.setGroupDescription(requestDTO.getGroupName());
        groupDTO.setCreatedBy(TaskTrackerConstant.TASK_ADMIN);
        groupDTO.setModifiedBy(TaskTrackerConstant.TASK_ADMIN);

        groupDTO.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
        System.out.println("groupDTO: "+groupDTO);
        GroupDTO groupDTOResponse = groupInfoService.createGroup(groupDTO);
        System.out.println("groupDTOResponse: "+groupDTOResponse);

        System.out.println("requestDTO: "+requestDTO);
        UserInfo userEntity=userMapper.userDTOToUserInfo(requestDTO);
        System.out.println("userEntity: "+userEntity);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        userEntity.setGroupId(groupDTOResponse.getGroupId());
        userEntity.setCreatedBy(TaskTrackerConstant.TASK_ADMIN);
        userEntity.setCreatedDate(timestamp);
        userEntity.setModifiedBy(TaskTrackerConstant.TASK_ADMIN);
        userEntity.setModifiedDate(timestamp);
        userEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
        userEntity.setPasswordChangeMandatory(TaskTrackerConstant.TASK_PASSWORD_CHANGE_MANDATORY_FALSE);
        UserDTO output= userMapper.userEntityToUserDTO(userInfoRepository.save(userEntity));
        output.setPassword(null);
        output.setOldPassword(null);
        output.setNewPassword(null);
        output.setAuthToken(null);

        return output;

    }

}
