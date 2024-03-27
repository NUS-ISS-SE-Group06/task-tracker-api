package com.nus.iss.tasktracker.dto;


import lombok.Data;

import java.sql.Timestamp;

@Data
public class TaskDTO {

    private Integer taskId;

    private Timestamp taskDueDate;

    private Integer taskAssignee;

    private Integer taskCategoryId;

    private String taskDescription;

    private String taskName;

    private String taskPriority;

    private Integer taskRewardPoint;

    private String taskStatus;

    private String createdBy;

    private String modifiedBy;

    private String taskAssigneeName;
    private String taskCategoryName;

}



