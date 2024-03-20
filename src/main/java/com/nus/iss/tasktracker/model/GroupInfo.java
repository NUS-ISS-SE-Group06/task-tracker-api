package com.nus.iss.tasktracker.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
public class GroupInfo {


    @Getter
    @Setter
    @Id
    @Column(name = "groupid")
    private int groupId;

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
    @Column(name = "delete_flag")
    private String deleteFlag;

}
