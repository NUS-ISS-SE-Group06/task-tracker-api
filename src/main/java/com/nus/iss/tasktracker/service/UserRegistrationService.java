package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.UserDTO;
import org.springframework.stereotype.Service;


public interface UserRegistrationService {
    public UserDTO getUserById(int id);
}
