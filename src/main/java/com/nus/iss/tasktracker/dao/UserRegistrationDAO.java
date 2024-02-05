package com.nus.iss.tasktracker.dao;

import com.nus.iss.tasktracker.model.UserEntity;

public interface UserRegistrationDAO {
    UserEntity getUserInfoById(int id);
}
