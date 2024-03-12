package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.UserDTO;

public interface UserInfoService {
    UserDTO UserLogin(UserDTO requestDTO);
}
