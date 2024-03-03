package com.nus.iss.tasktracker.dto;


import lombok.Data;

@Data
public class UserDTO {

    private int id;

    private String userName;

    private String userRole;

    private  String password;


}
