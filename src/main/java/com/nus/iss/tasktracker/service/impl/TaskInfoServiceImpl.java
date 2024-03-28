package com.nus.iss.tasktracker.service.impl;
import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.mapper.TaskInfoMapper;
import com.nus.iss.tasktracker.mapper.TaskMapper;
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
import com.nus.iss.tasktracker.dto.LeaderBoardDTO;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Objects;
import com.nus.iss.tasktracker.model.LeaderBoardQueryInfo;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class TaskInfoServiceImpl implements TaskInfoService {

    private final TaskInfoMapper taskInfoMapper;
    private final TaskInfoRepository taskInfoRepository;

    private final UserInfoRepository userInfoRepository;
    private final TaskMapper taskMapper;

    public TaskInfoServiceImpl(TaskInfoMapper taskInfoMapper, TaskInfoRepository taskInfoRepository, UserInfoRepository userInfoRepository, TaskMapper taskMapper) {
        this.taskInfoMapper = taskInfoMapper;
        this.taskInfoRepository = taskInfoRepository;
        this.userInfoRepository = userInfoRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional
    public TaskInfoDTO createTask(TaskInfoDTO requestDTO) {
        // Check if taskName is empty
        if (StringUtils.isEmpty(requestDTO.getTaskName())) {
            throw new RuntimeException("Name - Please input value!");
        }

        // Check if taskDescription is empty
        if (StringUtils.isEmpty(requestDTO.getTaskDescription())) {
            throw new RuntimeException("Description - Please input value!");
        }

        // Check if taskAssignee is 0
        if (requestDTO.getTaskAssignee() == 0) {
            throw new RuntimeException("Assignee - Please input value!");
        }

        // Check if taskDueDate is null
        if (requestDTO.getTaskDueDate() == null) {
            throw new RuntimeException("DueDate - Please input value!");
        }

        // Convert LocalDateTime to Timestamp

        Timestamp dueDateTimestamp = taskInfoMapper.toTimestamp(requestDTO.getTaskDueDate().toLocalDateTime());


        // Ensure the date is not in the past
        LocalDateTime now = LocalDateTime.now();
        if (requestDTO.getTaskDueDate().toLocalDateTime().isBefore(now)) {
            throw new RuntimeException("DueDate - Due date cannot be in the past!");
        }

        // Create a new TaskInfoDTO object
        TaskInfo taskInfoEntity = taskInfoMapper.taskInfoToEntity(requestDTO);

        taskInfoEntity.setTaskDueDate(dueDateTimestamp);
        // Set default value for delete flag
        taskInfoEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);

        // Get the values from the jwt token for created by/modified by
        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();

        if (!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)) {
            throw new RuntimeException("Service Accessed Without Token");
        }

        if (userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            UserInfo currentUserInfo = userInfoRepository.findByUsername(userName);
            if (currentUserInfo != null) {
                taskInfoEntity.setCreatedBy(currentUserInfo.getCreatedBy());
                taskInfoEntity.setModifiedBy(currentUserInfo.getCreatedBy());
            } else {
                throw new RuntimeException("User Info unavailable in DB");
            }
        }

        // Set createdDate and modifiedDate
        taskInfoEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
        taskInfoEntity.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

        // Save and map to dto
        TaskInfoDTO savedTaskInfoDTO = taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoRepository.save(taskInfoEntity));

        return savedTaskInfoDTO;
    }
public TaskInfoDTO updateTask(int taskId,TaskInfoDTO requestDTO){

        //check if taskId is valid
     if(taskId <= 0){
         throw new RuntimeException("TaskId invalid!");
     }
    // Check if taskName is empty
    if (StringUtils.isEmpty(requestDTO.getTaskName())) {
        throw new RuntimeException("Name - Please input value!");
    }

    // Check if taskDescription is empty
    if (StringUtils.isEmpty(requestDTO.getTaskDescription())) {
        throw new RuntimeException("Description - Please input value!");
    }

    // Check if taskAssignee is 0
    if (requestDTO.getTaskAssignee() == 0) {
        throw new RuntimeException("Assignee - Please input value!");
    }

    // Check if taskDueDate is null
    if (requestDTO.getTaskDueDate() == null) {
        throw new RuntimeException("DueDate - Please input value!");
    }

    // Convert LocalDateTime to Timestamp

    Timestamp dueDateTimestamp = taskInfoMapper.toTimestamp(requestDTO.getTaskDueDate().toLocalDateTime());


    // Ensure the date is not in the past
    LocalDateTime now = LocalDateTime.now();
    if (requestDTO.getTaskDueDate().toLocalDateTime().isBefore(now)) {
        throw new RuntimeException("DueDate - Due date cannot be in the past!");
    }
    Optional<TaskInfo> optionalTaskInfo  = taskInfoRepository.findById(taskId);
    TaskInfo taskInfoEntity = optionalTaskInfo.get();
    if (taskInfoEntity != null) {
        // Update taskInfoEntity with values from requestDTO
        taskInfoEntity.setTaskName(requestDTO.getTaskName());
        taskInfoEntity.setTaskDescription(requestDTO.getTaskDescription());
        taskInfoEntity.setTaskPriority(requestDTO.getTaskPriority());
        taskInfoEntity.setTaskCategoryId(requestDTO.getTaskCategoryId());
        taskInfoEntity.setTaskDueDate(dueDateTimestamp);
        taskInfoEntity.setTaskAssignee(requestDTO.getTaskAssignee());
        taskInfoEntity.setTaskRewardPoint(requestDTO.getTaskRewardPoint());
        taskInfoEntity.setTaskStatus(requestDTO.getTaskStatus());
        // Set default value for delete flag
        taskInfoEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
        // Set  modifiedDate
        taskInfoEntity.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));

    }else{
        throw new RuntimeException("Task not found!");
    }

    // Get the values from the jwt token for created by/modified by
    String userName = TaskTrackerInterceptor.getLoggedInUserName();
    String userRole = TaskTrackerInterceptor.getLoggedInUserRole();

    if (!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)) {
        throw new RuntimeException("Service Accessed Without Token");
    }

    if (userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
        UserInfo currentUserInfo = userInfoRepository.findByUsername(userName);
        if (currentUserInfo != null) {
            taskInfoEntity.setCreatedBy(currentUserInfo.getCreatedBy());
            taskInfoEntity.setModifiedBy(currentUserInfo.getCreatedBy());
        } else {
            throw new RuntimeException("User Info unavailable in DB");
        }
    }



    // Save and map to dto
    TaskInfoDTO savedTaskInfoDTO = taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoRepository.save(taskInfoEntity));

    return savedTaskInfoDTO;



}

    @Override
    public List<LeaderBoardDTO> findTaskRewardPointsByGroupId(Integer groupId) {
        List<LeaderBoardQueryInfo> result = new ArrayList<>();
        List<Object[]> queryResult = taskInfoRepository.findTaskRewardPointsByGroupId(groupId);
        int rowId=0;
        for(Object[] row: queryResult) {
            rowId++;

            // column index : 0 user_id, 1 name, 2 group_id, 3 group_name, 4 task_reward_point
            LeaderBoardQueryInfo record = new LeaderBoardQueryInfo();
            record.setId((Integer) rowId);
            record.setUserId((Integer) row[0]);
            record.setName((String) row[1]);
            record.setGroupId((Integer) row[2]);
            record.setGroupName((String) row[3]);
            record.setTaskRewardPoint((Integer) row[4]);
            result.add(record);
        }

        return taskMapper.leaderBoardQueryInfoListToLeaderBoardDTOList(result);
    }
    @Override
    @Transactional
    public TaskInfoDTO deleteTask(int id){
        //check for the jwt
        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();

        if(!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)){
            throw new RuntimeException("Service Accessed Without Token");
        }

        //retrieve the info
        Optional<TaskInfo> optionalTaskInfo = taskInfoRepository.findById(id);
        if (optionalTaskInfo.isPresent()) {
            //check if the id exists
            TaskInfo taskInfoEntity = optionalTaskInfo.get();
            //check if the use is admin
            if(userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
                UserInfo currentUserInfo = userInfoRepository.findByUsername(userName);
                if (currentUserInfo != null) {
                    taskInfoEntity.setCreatedBy(currentUserInfo.getCreatedBy());
                    taskInfoEntity.setModifiedBy(currentUserInfo.getCreatedBy());
                } else {
                    throw new RuntimeException("User Info unavailable in DB");
                }
            }
            //set create/modified dates
            taskInfoEntity.setCreatedDate(Timestamp.valueOf(LocalDateTime.now()));
            taskInfoEntity.setModifiedDate(Timestamp.valueOf(LocalDateTime.now()));
            //set delete flag
            taskInfoEntity.setDeleteFlag(TaskTrackerConstant.DELETE_FLAG_TRUE);
            //save the info
            taskInfoRepository.save(taskInfoEntity);
            return taskInfoMapper.taskInfoToTaskinfoDTO(taskInfoEntity);
        }
        else{
            // If the task does not exist, throw an exception or handle the error as needed
            throw new RuntimeException("Task with ID " + id + " not found");
        }

    }
    @Override
    public List<TaskInfoDTO> getAllActiveTasks(){

        String userName = TaskTrackerInterceptor.getLoggedInUserName();
        String userRole = TaskTrackerInterceptor.getLoggedInUserRole();


        if(!StringUtils.hasText(userName) || !StringUtils.hasText(userRole)){
            throw new RuntimeException("Service Accessed Without Token");
        }
        if(userRole.equals(TaskTrackerConstant.REGISTRATION_ROLE_ADMIN)) {
            UserInfo currentUserInfo = userInfoRepository.findByUsername(userName);
            if (currentUserInfo != null) {
                return taskInfoRepository.findTaskByDeleteFlag(TaskTrackerConstant.DELETE_FLAG_FALSE);
            }
            else{
                throw new RuntimeException("User not found!");
            }

        }
        else{
            throw new RuntimeException("Not Allowed to Access!");
        }


    }
}




