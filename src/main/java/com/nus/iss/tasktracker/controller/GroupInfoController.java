package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.GroupInfoDTO;
import com.nus.iss.tasktracker.mapper.GroupInfoMapper;
import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.service.GroupInfoService;
import com.nus.iss.tasktracker.util.CrudStatus;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/groupinfo")
public class GroupInfoController {
    private final GroupInfoService groupInfoService;
    private final GroupInfoMapper groupInfoMapper;
    @Autowired
    public GroupInfoController(GroupInfoService groupInfoService, GroupInfoMapper groupInfoMapper){
        this.groupInfoService=groupInfoService;
        this.groupInfoMapper=groupInfoMapper;
    }

    @PostMapping
    @ApiOperation("Create a new GroupInfo")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_CREATED),
            @ApiResponse(code = 400, message = CrudStatus.NO_RECORD_CREATED)
    })
    public ResponseEntity<Object> createGroupInfo(@RequestBody GroupInfoDTO groupInfoDto){
        GroupInfo groupInfo=groupInfoMapper.groupInfoDtoToGroupInfo(groupInfoDto);
        String result=groupInfoService.createGroupInfo(groupInfo);
        if(result.equals(CrudStatus.RECORD_CREATED)){
            return ResponseEntity.status(HttpStatus.CREATED)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(CrudStatus.RECORD_CREATED_JSON);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(CrudStatus.NO_RECORD_CREATED_JSON);
        }
    }

    @PutMapping("/{id}")
    @ApiOperation("Update an existing GroupInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_AFFECTED),
            @ApiResponse(code = 200, message = CrudStatus.NO_RECORD_AFFECTED)
    })
    public ResponseEntity<Object> updateGroupInfo(@PathVariable Integer id, @RequestBody GroupInfoDTO groupInfoDto) {
        GroupInfo groupInfo=groupInfoMapper.groupInfoDtoToGroupInfo(groupInfoDto);
        String result=groupInfoService.updateGroupInfo(id,groupInfo);

        String crudStatus = result.equals(CrudStatus.RECORD_AFFECTED) ? CrudStatus.RECORD_AFFECTED_JSON : CrudStatus.NO_RECORD_AFFECTED;

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(crudStatus);

    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an existing GroupInfo")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = CrudStatus.RECORD_DELETED),
            @ApiResponse(code=200, message = CrudStatus.NO_RECORD_DELETED)
    })
    public ResponseEntity<Object> deleteGroupInfo(@PathVariable Integer id) {
        String result=groupInfoService.deleteGroupInfo(id);

        String crudStatus = result.equals(CrudStatus.RECORD_DELETED) ? CrudStatus.RECORD_DELETED_JSON : CrudStatus.NO_RECORD_DELETED_JSON;


        return ResponseEntity.ok()
                .contentType (MediaType.APPLICATION_JSON)
                .body(crudStatus);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get GroupInfo object by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of GroupInfo objects")
    })
    public ResponseEntity<GroupInfoDTO> readGroupInfo(@PathVariable Integer id) {
        GroupInfo result= groupInfoService.readGroupInfo(id);
        GroupInfoDTO resultDto=groupInfoMapper.groupInfoToGroupInfoDTO(result);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }

    @GetMapping
    @ApiOperation("Get all GroupInfo object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of GroupInfo objects")
    })
    public ResponseEntity<List<GroupInfoDTO>> readGroupInfo() {
        List<GroupInfo> result= groupInfoService.readGroupInfo();
        List<GroupInfoDTO> resultDto=result.stream()
                .map(groupInfoMapper::groupInfoToGroupInfoDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }




}




