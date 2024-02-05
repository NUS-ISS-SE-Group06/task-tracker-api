package com.nus.iss.tasktracker.model;


import lombok.Data;

//@Entity
@Data
public class UserEntity {

    private int id;

    private String userName;

    private String userRole;

}
