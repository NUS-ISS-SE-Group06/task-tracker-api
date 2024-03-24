package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.UserDTO;


public interface UserRegistrationService {
    public UserDTO getUserById(int id);
    public void changePassword(UserDTO requestDTO);
    public UserDTO signUp(UserDTO requestDTO);
}
