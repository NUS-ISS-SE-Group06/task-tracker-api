package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    String createUserInfo(UserInfo userInfo);
    List<UserInfo> readUserInfo();
    UserInfo readUserInfo(Integer id);
    String updateUserInfo(Integer id, UserInfo userInfo);
    String updateUserPassword(Integer id, String password);
    String deleteUserInfo(Integer id);
    String hardDeleteUserInfo(Integer id);
}
