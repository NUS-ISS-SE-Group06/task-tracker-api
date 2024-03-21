package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.UserDTO;

public interface GroupInfoService {

    public GroupDTO getGroupById(int groupId);

    public GroupDTO createGroup(GroupDTO groupDTO);
}
