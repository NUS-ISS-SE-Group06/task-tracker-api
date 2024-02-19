package com.nus.iss.tasktracker.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TaskInfoDTO {
    private Integer taskId;
    private String taskName;
    private String taskDescription;
    private String taskPriority;
    private String taskCategoryId;
    private LocalDateTime taskDueDate;
    private Integer taskAssignee;
    private Integer taskRewardPoint;
    private String taskStatus;

}

