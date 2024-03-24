package com.nus.iss.tasktracker.dto;

import lombok.Data;


@Data
public class LeaderBoardDTO {
    private Integer id;
    private Integer userId;
    private String name;
    private Integer groupId;
    private String groupName;
    private Integer taskRewardPoint;

}







