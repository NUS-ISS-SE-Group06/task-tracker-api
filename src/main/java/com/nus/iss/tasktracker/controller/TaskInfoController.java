package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.mapper.TaskInfoMapper;
import com.nus.iss.tasktracker.model.TaskInfo;
import com.nus.iss.tasktracker.service.TaskInfoService;
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
@RequestMapping("/taskinfo")
public class TaskInfoController {
    private final TaskInfoService taskInfoService;
    private final TaskInfoMapper taskInfoMapper;
    @Autowired
    public TaskInfoController(TaskInfoService taskInfoService, TaskInfoMapper taskInfoMapper){
        this.taskInfoService=taskInfoService;
        this.taskInfoMapper=taskInfoMapper;
    }

    @PostMapping
    @ApiOperation("Create a new TaskInfo")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_CREATED),
            @ApiResponse(code = 400, message = CrudStatus.NO_RECORD_CREATED)
    })
    public ResponseEntity<Object> createTaskInfo(@RequestBody TaskInfoDTO taskInfoDto){
        TaskInfo taskInfo=taskInfoMapper.taskInfoDtoToTaskInfo(taskInfoDto);
        String result=taskInfoService.createTaskInfo(taskInfo);
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
    @ApiOperation("Update an existing TaskInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_AFFECTED),
            @ApiResponse(code = 200, message = CrudStatus.NO_RECORD_AFFECTED)
    })
    public ResponseEntity<Object> updateTaskInfo(@PathVariable Integer id, @RequestBody TaskInfoDTO taskInfoDto) {
        TaskInfo taskInfo=taskInfoMapper.taskInfoDtoToTaskInfo(taskInfoDto);
        String result=taskInfoService.updateTaskInfo(id,taskInfo);

        String crudStatus = result.equals(CrudStatus.RECORD_AFFECTED) ? CrudStatus.RECORD_AFFECTED_JSON : CrudStatus.NO_RECORD_AFFECTED;

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(crudStatus);

    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an existing TaskInfo")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = CrudStatus.RECORD_DELETED),
            @ApiResponse(code=200, message = CrudStatus.NO_RECORD_DELETED)
    })
    public ResponseEntity<Object> deleteTaskInfo(@PathVariable Integer id) {
        String result=taskInfoService.deleteTaskInfo(id);

        String crudStatus = result.equals(CrudStatus.RECORD_DELETED) ? CrudStatus.RECORD_DELETED_JSON : CrudStatus.NO_RECORD_DELETED_JSON;


        return ResponseEntity.ok()
                .contentType (MediaType.APPLICATION_JSON)
                .body(crudStatus);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get TaskInfo object by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of TaskInfo objects")
    })
    public ResponseEntity<TaskInfoDTO> readTaskInfo(@PathVariable Integer id) {
        TaskInfo result= taskInfoService.readTaskInfo(id);
        TaskInfoDTO resultDto=taskInfoMapper.taskInfoToTaskInfoDTO(result);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }

    @GetMapping
    @ApiOperation("Get all TaskInfo object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of TaskInfo objects")
    })
    public ResponseEntity<List<TaskInfoDTO>> readTaskInfo() {
        List<TaskInfo> result= taskInfoService.readTaskInfo();
        List<TaskInfoDTO> resultDto=result.stream()
                .map(taskInfoMapper::taskInfoToTaskInfoDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }




}




