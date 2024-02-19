package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.GroupInfo;

import java.util.List;

public interface GroupInfoService {
    public String createGroupInfo(GroupInfo groupInfo);
    public List<GroupInfo> readGroupInfo();
    public GroupInfo readGroupInfo(Integer id);
    public String updateGroupInfo(Integer id, GroupInfo groupInfo);
    public String deleteGroupInfo(Integer id);
    public String hardDeleteGroupInfo(Integer id);
}
