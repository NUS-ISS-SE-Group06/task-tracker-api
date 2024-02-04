package com.nus.iss.tasktracker.dao.impl;


import com.nus.iss.tasktracker.dao.UserRegistrationDAO;
import com.nus.iss.tasktracker.model.UserEntity;
import org.springframework.stereotype.Repository;

@Repository
public class UserRegistrationDAOImpl implements UserRegistrationDAO {
    @Override
    public UserEntity getUserInfoById(int id) {

        // FIXME - MOCK CODE; TO BE REMOVED LATER
        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setUserName("Balaji-Britta-YewHoon-Yudi-William");
        userEntity.setUserRole("ADMIN");
        return userEntity;
    }
}
