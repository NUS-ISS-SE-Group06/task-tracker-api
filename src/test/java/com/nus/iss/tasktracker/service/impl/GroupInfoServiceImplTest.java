package com.nus.iss.tasktracker.service.impl;


import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.repository.GroupInfoRepository;
import com.nus.iss.tasktracker.util.CrudStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GroupInfoServiceImplTest {


    @Mock
    private GroupInfoRepository groupInfoRepository;

    @InjectMocks
    private GroupInfoServiceImpl groupInfoService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void createGroupInfo_Success() {
        GroupInfo groupInfo =new GroupInfo();
        groupInfo.setGroupId(1);
        groupInfo.setGroupName("Test");

        when(groupInfoRepository.existsByGroupName(any())).thenReturn(false);
        when(groupInfoRepository.findMaxId()).thenReturn(1);
        when(groupInfoRepository.save(any())).thenReturn(groupInfo);

        String output = groupInfoService.createGroupInfo(groupInfo);

        assertNotNull(output);
    }

   // @Test
/*    void createGroupInfo_Fail() {
        GroupInfo groupInfo =new GroupInfo();
        groupInfo.setGroupId(1);
        groupInfo.setGroupName("Test");

        when(groupInfoRepository.existsByGroupName(any())).thenReturn(false);
        when(groupInfoRepository.findMaxId()).thenReturn(1);
        when(groupInfoRepository.save(any())).thenReturn(null);
        when(groupInfoService.createGroupInfo(any())).thenReturn(null);

        String output = groupInfoService.createGroupInfo(groupInfo);

        assertNotNull(output);
    }*/


}
