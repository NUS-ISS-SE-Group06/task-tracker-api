package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.repository.UserInfoRepository;
import com.nus.iss.tasktracker.service.UserInfoService;
import com.nus.iss.tasktracker.util.CrudStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class UserInfoServiceImpl implements UserInfoService {

    private final UserInfoRepository userInfoRepository;

    @Autowired
    public UserInfoServiceImpl(UserInfoRepository userInfoRepository){ this.userInfoRepository=userInfoRepository;}


    @Override
    @Transactional
    public String createUserInfo(UserInfo userInfo) {
        try{
            if(!userInfoRepository.existsByUserName(userInfo.getUserName())){
                userInfo.setUserId(userInfoRepository.findMaxId()+1);
                userInfo.setCreatedDate(LocalDateTime.now());
                userInfoRepository.save(userInfo);
                return CrudStatus.RECORD_CREATED;
            }
        }catch(Exception e){
            log.debug(e.getMessage());
        }
        return CrudStatus.NO_RECORD_CREATED;
    }

    @Override
    public List<UserInfo> readUserInfo() {
        return userInfoRepository.findByDeletedFlagFalse();
    }

    @Override
    public UserInfo readUserInfo(Integer id) {
        Optional<UserInfo> opt = userInfoRepository.findByUserIdAndDeletedFlag(id,false);
        return opt.orElse(null);
    }

    @Override
    public String updateUserInfo(Integer id, UserInfo userInfo) {
        Optional<UserInfo> opt =userInfoRepository.findById(id);
        if (opt.isPresent()){
            UserInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setGroupId(userInfo.getGroupId());
                record.setName(userInfo.getName());
                record.setEmail(userInfo.getEmail());
                record.setRole(userInfo.getRole());
                record.setModifiedDate(LocalDateTime.now());
                userInfoRepository.save(record);
                return CrudStatus.RECORD_AFFECTED;
            }
        }
        return CrudStatus.NO_RECORD_AFFECTED;
    }

    @Override
    public String updateUserPassword(Integer id, String password) {
        Optional<UserInfo> opt =userInfoRepository.findById(id);
        if (opt.isPresent()){
            UserInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setPassword(password);
                record.setModifiedDate(LocalDateTime.now());
                userInfoRepository.save(record);
                return CrudStatus.RECORD_AFFECTED;
            }
        }
        return CrudStatus.NO_RECORD_AFFECTED;
    }

    @Override
    public String deleteUserInfo(Integer id) {
        Optional<UserInfo> opt =userInfoRepository.findById(id);
        if (opt.isPresent()){
            UserInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setDeletedFlag(true);
                record.setModifiedDate(LocalDateTime.now());
                userInfoRepository.save(record);
                return CrudStatus.RECORD_DELETED;
            }
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

    @Override
    public String hardDeleteUserInfo(Integer id) {
        Optional<UserInfo> opt =userInfoRepository.findById(id);
        if (opt.isPresent()){
            userInfoRepository.deleteById(id);
            return CrudStatus.RECORD_DELETED;
        }
        return CrudStatus.NO_RECORD_DELETED;
    }



}
