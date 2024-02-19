package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.GroupInfoDTO;
import com.nus.iss.tasktracker.mapper.GroupInfoMapper;
import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.service.GroupInfoService;
import com.nus.iss.tasktracker.util.CrudStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GroupInfoControllerTest {

    private static final String RECORD_CREATED="record created";
    private static final String NO_RECORD_CREATED="no record created";

    private static final String RECORD_CREATED_JSON="{\"message\": \"record created\"}";
    private static final String NO_RECORD_CREATED_JSON="{\"message\": \"no record created\"}";

    @Mock
    private GroupInfoService groupInfoService;
    @Mock
    private GroupInfoMapper groupInfoMapper;

    @InjectMocks
    private GroupInfoController groupInfoController;

    @BeforeEach
    void setUp(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createGroupInfo_ValidInput_ReturnOk(){
        GroupInfoDTO groupInfoDto= new GroupInfoDTO();

        when(groupInfoMapper.groupInfoDtoToGroupInfo(any())).thenReturn(new GroupInfo());
        when(groupInfoService.createGroupInfo(any())).thenReturn(RECORD_CREATED);

        ResponseEntity<Object> response = groupInfoController.createGroupInfo(groupInfoDto);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(RECORD_CREATED_JSON,response.getBody());
    }


    @Test
    void updateGroupInfo_ValidInput_ReturnOk(){
        Integer id=1;
        GroupInfoDTO groupInfoDto=new GroupInfoDTO();

        when(groupInfoMapper.groupInfoDtoToGroupInfo(groupInfoDto)).thenReturn(new GroupInfo());
        when(groupInfoService.updateGroupInfo(any(),any())).thenReturn(CrudStatus.RECORD_AFFECTED);

        ResponseEntity<Object> response = groupInfoController.updateGroupInfo(id,groupInfoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(CrudStatus.RECORD_AFFECTED_JSON,response.getBody());
    }



}
