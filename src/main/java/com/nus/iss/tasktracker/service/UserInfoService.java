package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.UserInfo;

import java.util.List;

public interface UserInfoService {
    public String createUserInfo(UserInfo userInfo);
    public List<UserInfo> readUserInfo();
    public UserInfo readUserInfo(Integer id);
    public String updateUserInfo(Integer id, UserInfo userInfo);
    public String updateUserPassword(Integer id, String password);
    public String deleteUserInfo(Integer id);
    public String hardDeleteUserInfo(Integer id);
}
