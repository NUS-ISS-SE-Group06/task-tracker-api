package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;


public interface TaskInfoService {
    TaskInfoDTO createTask(TaskInfoDTO requestDTO);
}
