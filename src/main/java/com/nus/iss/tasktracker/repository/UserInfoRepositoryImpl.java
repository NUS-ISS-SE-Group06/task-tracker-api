package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.UserEntity;

public class UserInfoRepositoryImpl implements UserInfoRepository {
    // Implement findByUsername method to interact with the database or any data store
    @Override
    public UserEntity findByUsername(String username) {
        // Database query or other data store interaction to find user by username
        return null;
    }

    public UserEntity save(UserEntity UserEntity){
        return UserEntity;
    }
}