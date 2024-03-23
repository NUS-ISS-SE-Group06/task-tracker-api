package com.nus.iss.tasktracker.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.sql.Timestamp;

@Entity
public class UserInfo {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long userId;

    @Getter
    @Setter
    private long groupId;

    @Getter
    @Setter
    private String email;

    @Getter
    @Setter
    private String name;

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
    private  String passwordChangeMandatory;

    @Getter
    @Setter
    private  String createdBy;

    @Getter
    @Setter
    private  Timestamp createdDate;

    @Getter
    @Setter
    private  String modifiedBy;

    @Getter
    @Setter
    private  Timestamp modifiedDate;

    @Getter
    @Setter
    private String deleteFlag;

    @Getter
    @Setter
    @Transient
    private String groupName;


    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userId +
                ", group_id='" + groupId + '\'' +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", role='" + role + '\'' +
                ", created_by='" + createdBy + '\'' +
                ", created_date='" + createdDate + '\'' +
                ", modified_by='" + modifiedBy + '\'' +
                ", modified_date='" + modifiedDate + '\'' +
                ", delete_flag='" + deleteFlag + '\'' +

                '}';
    }


}
