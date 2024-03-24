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
    @Column(name = "groupid")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long groupId;

    @Getter
    @Setter
    @Column(name = "groupname")
    private String groupName;

    @Getter
    @Setter
    @Column(name = "groupdescription")
    private  String groupDescription;

    @Getter
    @Setter
    @Column(name = "createdby")
    private  String createdBy;

    @Getter
    @Setter
    @Column(name = "modifiedby")
    private  String modifiedBy;

    @Getter
    @Setter
    @Column(name = "deleteflag")
    private String deleteFlag;

}
