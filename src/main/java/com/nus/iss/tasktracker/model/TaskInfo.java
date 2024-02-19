package com.nus.iss.tasktracker.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
public class TaskInfo {
    public TaskInfo() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Integer taskId;
    @Column(name = "task_name")
    private String taskName;
    @Column(name = "task_description")
    private String taskDescription;
    @Column(name = "task_priority")
    private String taskPriority;
    @Column(name = "task_category_id")
    private String taskCategoryId;
    @Column(name = "task_duedate")
    private LocalDateTime taskDueDate;
    @Column(name = "task_assignee")
    private Integer taskAssignee;
    @Column(name = "task_reward_point")
    private Integer taskRewardPoint;
    @Column(name = "task_status")
    private String taskStatus;
    @Column(name = "created_by")
    private String createdBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private LocalDateTime createdDate;
    @Column(name = "modified_by")
    private String modifiedBy;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;
    @Column(name = "deleted_flag")
    private boolean deletedFlag;

    @Override
    public String toString() {
        return "TaskInfo{" +
                "taskId=" + taskId +
                ", taskName='" + taskName + '\'' +
                ", taskDescription='" + taskDescription + '\'' +
                ", taskPriority='" + taskPriority + '\'' +
                ", taskCategoryId='" + taskCategoryId + '\'' +
                ", taskDueDate=" + taskDueDate +
                ", taskAssignee=" + taskAssignee +
                ", taskRewardPoint=" + taskRewardPoint +
                ", taskStatus='" + taskStatus + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", createdDate=" + createdDate +
                ", modifiedBy='" + modifiedBy + '\'' +
                ", modifiedDate=" + modifiedDate +
                ", deletedFlag=" + deletedFlag +
                '}';
    }
}


/*
CREATE TABLE task_info(
   task_id INTEGER NOT NULL,
   task_name INTEGER,
   task_description TEXT,
   task_priority TEXT NOT NULL,
   task_cateogry_id INTEGER NOT NULL,
   task_due_date datetime default current_timestamp NOT NULL,
   task_assignee INTEGER,
   task_reward_pints INTEGER,
   task_status TEXT NOT NULL,
   created_by TEXT NOT NULL,
   created_date datetime default current_timestamp NOT NULL,
   modified_by TEXT,
   modifed_date datetime default current_timestamp NOT NULL,
    "delete_flag" TEXT NOT NULL
        CHECK( typeof("boolean") = "text" AND
               "boolean" IN ("TRUE","FALSE")
   ),
   PRIMARY KEY (task_id)

);*/
