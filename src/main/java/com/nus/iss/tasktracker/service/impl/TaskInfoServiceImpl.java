package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.model.TaskInfo;
import com.nus.iss.tasktracker.repository.TaskInfoRepository;
import com.nus.iss.tasktracker.service.TaskInfoService;
import com.nus.iss.tasktracker.util.CrudStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.config.Task;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class TaskInfoServiceImpl implements TaskInfoService {

    private final TaskInfoRepository taskInfoRepository;

    @Autowired
    public TaskInfoServiceImpl(TaskInfoRepository taskInfoRepository){ this.taskInfoRepository=taskInfoRepository;}


    @Override
    @Transactional
    public String createTaskInfo(TaskInfo taskInfo) {
        try{
            if(!taskInfoRepository.existsByTaskName(taskInfo.getTaskName())){
                taskInfo.setTaskId(taskInfoRepository.findMaxId()+1);
                taskInfo.setCreatedDate(LocalDateTime.now());
                //taskInfo.setCreatedBy();
                TaskInfo output= taskInfoRepository.save(taskInfo);

                if ( output != null )
                    return CrudStatus.RECORD_CREATED;

            }
        }catch(Exception e){
            log.debug(e.getMessage());
        }
        return CrudStatus.NO_RECORD_CREATED;
    }

    @Override
    @Transactional
    public List<TaskInfo> readTaskInfo() {
        return taskInfoRepository.findByDeletedFlagFalse();
    }

    @Override
    @Transactional
    public TaskInfo readTaskInfo(Integer id) {
        Optional<TaskInfo> opt = taskInfoRepository.findByTaskIdAndDeletedFlag(id,false);
        return opt.orElse(null);
    }

    @Override
    @Transactional
    public String updateTaskInfo(Integer id, TaskInfo taskInfo) {
        Optional<TaskInfo> opt =taskInfoRepository.findById(id);
        if (opt.isPresent()){
            TaskInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setTaskName(taskInfo.getTaskName());
                record.setTaskDescription(taskInfo.getTaskDescription());
                record.setTaskDescription(taskInfo.getTaskDescription());
                record.setTaskDueDate(taskInfo.getTaskDueDate());
                record.setTaskAssignee(taskInfo.getTaskAssignee());
                record.setTaskRewardPoint(taskInfo.getTaskRewardPoint());
                record.setTaskStatus(taskInfo.getTaskStatus());
                //record.setModifiedBy();
                record.setModifiedDate(LocalDateTime.now());
                TaskInfo output= taskInfoRepository.save(record);

                if ( output != null )
                    return CrudStatus.RECORD_AFFECTED;
            }
        }
        return CrudStatus.NO_RECORD_AFFECTED;
    }

    @Override
    @Transactional
    public String deleteTaskInfo(Integer id) {
        Optional<TaskInfo> opt =taskInfoRepository.findById(id);
        if (opt.isPresent()){
            TaskInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setDeletedFlag(true);
                record.setModifiedDate(LocalDateTime.now());
                Object output= taskInfoRepository.save(record);

                if (output !=null)
                    return CrudStatus.RECORD_DELETED;

            }
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

    @Override
    @Transactional
    public String hardDeleteTaskInfo(Integer id) {
        Optional<TaskInfo> opt =taskInfoRepository.findById(id);
        if (opt.isPresent()){
            taskInfoRepository.deleteById(id);

            boolean output =taskInfoRepository.existsById(id);
            if (!output )
                return CrudStatus.RECORD_DELETED;

        }
        return CrudStatus.NO_RECORD_DELETED;
    }

}
