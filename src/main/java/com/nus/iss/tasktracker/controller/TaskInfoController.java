package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.service.TaskInfoService;
import com.nus.iss.tasktracker.util.CustomResponseHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/taskinfo")
public class TaskInfoController {
    private final TaskInfoService taskInfoService;

    public TaskInfoController(TaskInfoService taskInfoService) {
        this.taskInfoService = taskInfoService;
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<Response> createTask(@RequestBody TaskInfoDTO requestDTO) throws RuntimeException {

        TaskInfoDTO taskInfoDTO =null;
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";
        try {
            taskInfoDTO=taskInfoService.createTask(requestDTO);
        } catch(Exception e) {
            successOrFailMessage = e.getMessage();
        }

        if (taskInfoDTO !=  null){
            responseBody = taskInfoDTO;
            successOrFailMessage = "Task Created Successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }

    }
}
