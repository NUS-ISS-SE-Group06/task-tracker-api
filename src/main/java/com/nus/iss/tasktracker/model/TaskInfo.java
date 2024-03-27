package com.nus.iss.tasktracker.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "task_info")
public class TaskInfo {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "task_id")
    private int taskId;

    @Column(name = "task_name")
    private String taskName;

    @Column(name = "task_description")
    private String taskDescription;

    @Column(name = "task_priority")
    private String taskPriority;

    @Column(name = "task_category_id")
    private int taskCategoryId;

    @Column(name = "task_due_date")
    private LocalDateTime taskDueDate;

    @Column(name = "task_assignee")
    private int taskAssignee;

    @Column(name = "task_reward_points")
    private int taskRewardPoints;

    @Column(name = "task_status")
    private String taskStatus;

    @Column(name = "created_by")
    private String createdBy;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "modified_by")
    private String modifiedBy;

    @Column(name = "modified_date")
    private LocalDateTime modifiedDate;

    @Column(name = "delete_flag")
    private String deleteFlag;

    // Constructors, getters, setters, etc. can be added as needed
}
