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
    @Column(name = "userId")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long userid;

    @Getter
    @Setter
    @Column(name = "group_id")
    private long groupId;

    @Getter
    @Setter
    @Column(name = "email")
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
    @Column(name = "password_change_mandatory")
    private  String passwordChangeMandatory;

    @Getter
    @Setter
    @Column(name = "created_by")
    private  String createdBy;

    @Getter
    @Setter
    @Column(name = "created_date")
    private  Timestamp createdDate;

    @Getter
    @Setter
    @Column(name = "modified_by")
    private  String modifiedBy;

    @Getter
    @Setter
    @Column(name = "modified_date")
    private  Timestamp modifiedDate;

    @Getter
    @Setter
    @Column(name = "delete_flag")
    private String deleteFlag;


    @Getter
    @Setter
    @Transient
    private String groupName;


    @Override
    public String toString() {
        return "UserInfo{" +
                "userid=" + userid +
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
