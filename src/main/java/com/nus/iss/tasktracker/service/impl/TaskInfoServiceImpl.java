package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.mapper.TaskInfoMapper;
import com.nus.iss.tasktracker.model.TaskInfo;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.TaskInfoRepository;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.TaskInfoService;
import com.nus.iss.tasktracker.util.TaskTrackerConstant;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.nus.iss.tasktracker.interceptor.TaskTrackerInterceptor;
import org.springframework.util.StringUtils;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    private final TaskInfoMapper taskInfoMapper;
    private final TaskInfoRepository taskInfoRepository;

    private final UserInfoRepository userInfoRepository;

    public TaskInfoServiceImpl(TaskInfoMapper taskInfoMapper, TaskInfoRepository taskInfoRepository, UserInfoRepository userInfoRepository) {
        this.taskInfoMapper = taskInfoMapper;
        this.taskInfoRepository = taskInfoRepository;
        this.userInfoRepository = userInfoRepository;
    }

    @Override
    @Transactional
    public TaskInfoDTO createTask(TaskInfoDTO requestDTO){

        if(Objects.equals(requestDTO.getTaskName(), "")){
            throw new RuntimeException("Name - Please input value!");
        }

        if(Objects.equals(requestDTO.getTaskDescription(), "")){
            throw new RuntimeException("Description - Please input value!");
        }

        if(requestDTO.getTaskAssignee() == 0){
            throw new RuntimeException("Assignee - Please input value!");
        }
        if(requestDTO.getTaskDueDate() == null){
            throw new RuntimeException("DueDate - Please input value!");
        }
        //validate the date /time format
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        String dueDateString = requestDTO.getTaskDueDate().format(formatter);
        try {
            LocalDateTime dueDate = LocalDateTime.parse(dueDateString, formatter);
        } catch (DateTimeParseException e) {
            throw new RuntimeException("DueDate - Invalid date format!");
        }
        // Ensure the date is not in the past
        LocalDateTime now = LocalDateTime.now();

        if (requestDTO.getTaskDueDate().isBefore(now)) {
            throw new RuntimeException("DueDate - Due date cannot be in the past!");
        }
        // Create a new TaskInfoDTO object
        TaskInfo taskInfoEntity = taskInfoMapper.taskInfoToEntity(requestDTO);
        //Set default value for delete flag
        taskInfoEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();
        if(!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)){
            throw new RuntimeException("Service Accessed Without Token");
        }

        if(userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            UserInfo currentUserInfo = userInfoRepository.findByUsername(userName);
            if (currentUserInfo != null) {
                taskInfoEntity.setCreatedBy(currentUserInfo.getCreatedBy());
                taskInfoEntity.setModifiedBy(currentUserInfo.getCreatedBy());
            } else {
                throw new RuntimeException("User Info unavailable in DB");
            }
        }
        taskInfoEntity.setCreatedDate(LocalDateTime.now());
        taskInfoEntity.setModifiedDate(LocalDateTime.now());

        //Save and map to dto
        TaskInfoDTO savedTaskInfoDTO= taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoRepository.save(taskInfoEntity));



        return savedTaskInfoDTO;
    }
}
