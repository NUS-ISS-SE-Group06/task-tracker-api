package com.nus.iss.tasktracker.dto;

import jakarta.persistence.*;
import lombok.Data;

@Data
public class UserInfoDTO {
    private Integer userId;
    private String groupId;
    private String email;
    private String name;
    private String userName;
    private String role;
    private boolean isChangePassword;

}


