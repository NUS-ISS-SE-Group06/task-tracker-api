package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.TaskInfo;

import java.util.List;

public interface TaskInfoService {
    String createTaskInfo(TaskInfo taskInfo);
    List<TaskInfo> readTaskInfo();
    TaskInfo readTaskInfo(Integer id);
    String updateTaskInfo(Integer id, TaskInfo taskInfo);
    String deleteTaskInfo(Integer id);
    String hardDeleteTaskInfo(Integer id);
}
