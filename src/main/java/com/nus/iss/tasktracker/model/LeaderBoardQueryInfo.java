package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class LeaderBoardQueryInfo {
    @Transient
    private Integer id;

    @Transient
    private Integer userId;

    @Transient
    private String name;

    @Transient
    private Integer groupId;

    @Transient
    private String groupName;

    @Transient
    private Integer taskRewardPoint;

}




