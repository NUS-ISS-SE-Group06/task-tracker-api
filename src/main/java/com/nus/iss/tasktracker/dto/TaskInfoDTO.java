package com.nus.iss.tasktracker.dto;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.sql.Timestamp;
import java.time.LocalDateTime;
@Data
public class TaskInfoDTO {
    private int taskId;
    private String taskName;
    private String taskDescription;
    private String taskPriority;
    private int taskCategoryId;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp taskDueDate;
    private int taskAssignee;
    private int taskRewardPints;
    private String taskStatus;


    private String createdBy;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp createdDate;
    private String modifiedBy;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss")
    private Timestamp modifiedDate;
    private String deleteFlag;

}
