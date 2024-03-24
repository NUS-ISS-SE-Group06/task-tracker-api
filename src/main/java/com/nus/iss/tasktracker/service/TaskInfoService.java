package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.LeaderBoardDTO;

import java.util.List;

public interface TaskInfoService {

    public List<LeaderBoardDTO> findTaskRewardPointsByGroupId(Integer groupId);

}
