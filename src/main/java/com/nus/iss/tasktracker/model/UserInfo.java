package com.nus.iss.tasktracker.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class UserInfo {

    @Getter
    @Setter
    @Id
    private int userid;

    @Getter
    @Setter
    private String username;

    @Getter
    @Setter
    private  String password;

    @Getter
    @Setter
    private  String role;

    @Getter
    @Setter
    @Column(name = "delete_flag")
    private String deleteFlag;

}
