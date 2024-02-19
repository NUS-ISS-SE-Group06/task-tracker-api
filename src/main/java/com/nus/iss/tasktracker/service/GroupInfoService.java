package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.GroupInfo;

import java.util.List;

public interface GroupInfoService {
    String createGroupInfo(GroupInfo groupInfo);
    List<GroupInfo> readGroupInfo();
    GroupInfo readGroupInfo(Integer id);
    String updateGroupInfo(Integer id, GroupInfo groupInfo);
    String deleteGroupInfo(Integer id);
    String hardDeleteGroupInfo(Integer id);
}
