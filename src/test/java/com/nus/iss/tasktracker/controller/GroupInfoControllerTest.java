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

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class GroupInfoControllerTest {

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
    void createGroupInfo_ValidInput_Return_RecordCreated(){
        GroupInfoDTO groupInfoDto= new GroupInfoDTO();

        when(groupInfoMapper.groupInfoDtoToGroupInfo(any())).thenReturn(new GroupInfo());
        when(groupInfoService.createGroupInfo(any())).thenReturn(CrudStatus.RECORD_CREATED);

        ResponseEntity<Object> response = groupInfoController.createGroupInfo(groupInfoDto);

        assertEquals(HttpStatus.CREATED,response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(CrudStatus.RECORD_CREATED_JSON,response.getBody());
    }

    @Test
    void createGroupInfo_InValidInput_Return_NoRecordCreated(){
        GroupInfoDTO groupInfoDto= new GroupInfoDTO();

        when(groupInfoMapper.groupInfoDtoToGroupInfo(any())).thenReturn(new GroupInfo());
        when(groupInfoService.createGroupInfo(any())).thenReturn(CrudStatus.NO_RECORD_CREATED);

        ResponseEntity<Object> response = groupInfoController.createGroupInfo(groupInfoDto);

        assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(CrudStatus.NO_RECORD_CREATED_JSON,response.getBody());
    }


    @Test
    void updateGroupInfo_ValidInput_Return_RecordAffected(){
        Integer id=1;
        GroupInfoDTO groupInfoDto=new GroupInfoDTO();

        when(groupInfoMapper.groupInfoDtoToGroupInfo(groupInfoDto)).thenReturn(new GroupInfo());
        when(groupInfoService.updateGroupInfo(any(),any())).thenReturn(CrudStatus.RECORD_AFFECTED);

        ResponseEntity<Object> response = groupInfoController.updateGroupInfo(id,groupInfoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(CrudStatus.RECORD_AFFECTED_JSON,response.getBody());
    }


    @Test
    void updateGroupInfo_ValidInput_Return_NoRecordAffected(){
        Integer id=1;
        GroupInfoDTO groupInfoDto=new GroupInfoDTO();

        when(groupInfoMapper.groupInfoDtoToGroupInfo(groupInfoDto)).thenReturn(new GroupInfo());
        when(groupInfoService.updateGroupInfo(any(),any())).thenReturn(CrudStatus.NO_RECORD_AFFECTED);

        ResponseEntity<Object> response = groupInfoController.updateGroupInfo(id,groupInfoDto);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(CrudStatus.NO_RECORD_AFFECTED,response.getBody());
    }


    @Test
    void deleteGroupInfo_ValidInput_Return_RecordDeleted(){
        when(groupInfoService.deleteGroupInfo(any())).thenReturn(CrudStatus.RECORD_DELETED);

        ResponseEntity<Object> response = groupInfoController.deleteGroupInfo(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(CrudStatus.RECORD_DELETED_JSON,response.getBody());
    }

    @Test
    void deleteGroupInfo_ValidInput_Return_NoRecordDeleted(){
        when(groupInfoService.deleteGroupInfo(any())).thenReturn(CrudStatus.NO_RECORD_DELETED);

        ResponseEntity<Object> response = groupInfoController.deleteGroupInfo(any());

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());
        assertEquals(CrudStatus.NO_RECORD_DELETED_JSON,response.getBody());
    }



    @Test
    void readGroupInfo_ValidInput_Return_a_Records(){
        GroupInfo testData=new GroupInfo();
        testData.setGroupId(1);
        testData.setGroupName("Test");
        testData.setGroupDescription("Test");

        GroupInfoDTO testDataDto=new GroupInfoDTO();
        testDataDto.setGroupId(1);
        testDataDto.setGroupName("Test");
        testDataDto.setGroupDescription("Test");


        when(groupInfoService.readGroupInfo(any())).thenReturn(testData);
        when(groupInfoMapper.groupInfoToGroupInfoDTO(any())).thenReturn(testDataDto);


        ResponseEntity<GroupInfoDTO> response =groupInfoController.readGroupInfo(any());


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());

        GroupInfoDTO output =(GroupInfoDTO) response.getBody();

        assertEquals("Test", output==null ?"":output.getGroupName());
        assertEquals("Test", output==null ?"":output.getGroupDescription());

    }




    @Test
    void readGroupInfo_ValidInput_Return_List_Of_Records(){

        List<GroupInfo> groupInfoList = new ArrayList<>();

        GroupInfo testData1=new GroupInfo();
        testData1.setGroupId(1);
        testData1.setGroupName("Test");
        testData1.setGroupDescription("Test");
        groupInfoList.add(testData1);

        GroupInfo testData2=new GroupInfo();
        testData2.setGroupId(2);
        testData2.setGroupName("Test2");
        testData2.setGroupDescription("Test2");
        groupInfoList.add(testData2);


        GroupInfoDTO testDataDto1=new GroupInfoDTO();
        testDataDto1.setGroupId(1);
        testDataDto1.setGroupName("Test");
        testDataDto1.setGroupDescription("Test");


        GroupInfoDTO testDataDto2=new GroupInfoDTO();
        testDataDto2.setGroupId(2);
        testDataDto2.setGroupName("Test2");
        testDataDto2.setGroupDescription("Test2");


        when(groupInfoService.readGroupInfo()).thenReturn(groupInfoList);
        when(groupInfoMapper.groupInfoToGroupInfoDTO(any()))
                .thenReturn(testDataDto1)
                .thenReturn(testDataDto2);

        ResponseEntity<List<GroupInfoDTO>> response =groupInfoController.readGroupInfo();


        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(MediaType.APPLICATION_JSON,response.getHeaders().getContentType());

        List<GroupInfoDTO> output = (List<GroupInfoDTO>)response.getBody();
        Integer count = output==null? 0:output.size();
        assertEquals(2, count);

    }


}
