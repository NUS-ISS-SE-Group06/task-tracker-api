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

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
        GroupInfo groupInfo=new GroupInfo();
        groupInfo.setGroupName("TestName");
        groupInfo.setGroupDescription("TestDescription");
        String result=groupInfoService.createGroupInfo(groupInfo);

        assertEquals(CrudStatus.RECORD_CREATED,result);
    }

    @Test
    void createGroupInfo_Fail() {
        GroupInfo groupInfo=new GroupInfo();
        groupInfo.setGroupName("TestName");
        groupInfo.setGroupDescription("TestDescription");

        when(groupInfoRepository.existsByGroupName(any())).thenReturn(true);

        String result=groupInfoService.createGroupInfo(groupInfo);

        assertEquals(CrudStatus.NO_RECORD_CREATED,result);
    }


}
