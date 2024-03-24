package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Entity
public class GroupInfo {


    @Getter
    @Setter
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long groupId;

    @Getter
    @Setter
    private String groupName;

    @Getter
    @Setter
    private  String groupDescription;

    @Getter
    @Setter
    private  String createdBy;

    @Getter
    @Setter
    private  String modifiedBy;

    @Getter
    @Setter
    private String deleteFlag;

}
