package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.LeaderBoardDTO;
import com.nus.iss.tasktracker.dto.TaskInfoDTO;

import java.util.List;

public interface TaskInfoService {

    TaskInfoDTO createTask(TaskInfoDTO requestDTO);
    public List<LeaderBoardDTO> findTaskRewardPointsByGroupId(Integer groupId);

    TaskInfoDTO deleteTask(int id);

    List<TaskInfoDTO> getAllActiveTasks();

    TaskInfoDTO updateTask(int taskId, TaskInfoDTO requestDTO);
}
