package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.repository.GroupInfoRepository;
import com.nus.iss.tasktracker.service.GroupInfoService;
import com.nus.iss.tasktracker.util.CrudStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class GroupInfoServiceImpl implements GroupInfoService {

    private final GroupInfoRepository groupInfoRepository;

    @Autowired
    public GroupInfoServiceImpl(GroupInfoRepository groupInfoRepository){ this.groupInfoRepository=groupInfoRepository;}


    @Override
    @Transactional
    public String createGroupInfo(GroupInfo groupInfo) {
        try{
            if(!groupInfoRepository.existsByGroupName(groupInfo.getGroupName())){
                groupInfo.setGroupId(groupInfoRepository.findMaxId()+1);
                groupInfo.setCreatedDate(LocalDateTime.now());
                groupInfoRepository.save(groupInfo);
                return CrudStatus.RECORD_CREATED;
            }
        }catch(Exception e){
            log.debug(e.getMessage());
        }
        return CrudStatus.NO_RECORD_CREATED;
    }

    @Override
    @Transactional
    public List<GroupInfo> readGroupInfo() {
        return groupInfoRepository.findByDeletedFlagFalse();
    }

    @Override
    @Transactional
    public GroupInfo readGroupInfo(Integer id) {
        Optional<GroupInfo> opt = groupInfoRepository.findByGroupIdAndDeletedFlag(id,false);
        return opt.orElse(null);
    }

    @Override
    @Transactional
    public String updateGroupInfo(Integer id, GroupInfo groupInfo) {
        Optional<GroupInfo> opt =groupInfoRepository.findById(id);
        if (opt.isPresent()){
            GroupInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setGroupName(groupInfo.getGroupName());
                record.setModifiedDate(LocalDateTime.now());
                groupInfoRepository.save(record);
                return CrudStatus.RECORD_AFFECTED;
            }
        }
        return CrudStatus.NO_RECORD_AFFECTED;
    }

    @Override
    @Transactional
    public String deleteGroupInfo(Integer id) {
        Optional<GroupInfo> opt =groupInfoRepository.findById(id);
        if (opt.isPresent()){
            GroupInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setDeletedFlag(true);
                record.setModifiedDate(LocalDateTime.now());
                groupInfoRepository.save(record);
                return CrudStatus.RECORD_DELETED;
            }
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

    @Override
    @Transactional
    public String hardDeleteGroupInfo(Integer id) {
        Optional<GroupInfo> opt =groupInfoRepository.findById(id);
        if (opt.isPresent()){
            groupInfoRepository.deleteById(id);
            return CrudStatus.RECORD_DELETED;
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

}
