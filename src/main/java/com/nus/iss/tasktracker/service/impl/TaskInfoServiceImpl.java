package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.LeaderBoardDTO;
import com.nus.iss.tasktracker.mapper.TaskMapper;
import com.nus.iss.tasktracker.model.LeaderBoardQueryInfo;
import com.nus.iss.tasktracker.repository.TaskInfoRepository;
import com.nus.iss.tasktracker.service.TaskInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class TaskInfoServiceImpl implements TaskInfoService {
    private  final TaskInfoRepository taskInfoRepository;
    private final TaskMapper taskMapper;

    @Autowired
    public TaskInfoServiceImpl(TaskInfoRepository taskInfoRepository, TaskMapper taskMapper) {
        this.taskInfoRepository = taskInfoRepository;
        this.taskMapper = taskMapper;
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
}



