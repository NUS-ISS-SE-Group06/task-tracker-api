package com.nus.iss.tasktracker.dto;


import lombok.Data;

@Data
public class UserDTO {

    private int userId;
    private String username;
    private String userRole;
    private String oldPassword;
    private String newPassword;
    private String password;
    private String authToken;

}
