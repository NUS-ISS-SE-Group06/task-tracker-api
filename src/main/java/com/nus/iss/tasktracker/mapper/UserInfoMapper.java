package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.UserInfoDTO;
import com.nus.iss.tasktracker.model.UserInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserInfoMapper {

    UserInfoDTO userInfoToUserInfoDTO(UserInfo userInfo);
    UserInfo userInfoDtoToUserInfo(UserInfoDTO userInfoDto);
}
