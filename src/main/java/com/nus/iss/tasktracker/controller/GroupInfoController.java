package com.nus.iss.tasktracker.controller;


import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.GroupInfoService;
import com.nus.iss.tasktracker.util.CustomResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/group-info")
@Slf4j
public class GroupInfoController {

    private final GroupInfoService groupInfoService;

    @Autowired
    public GroupInfoController(GroupInfoService groupInfoService) {
        this.groupInfoService = groupInfoService;
    }


    @GetMapping("/{id}")
    public GroupDTO getGroupById(@PathVariable int id){
        log.info("Group endpoint called with id {}", id);
        return groupInfoService.getGroupById(id);
    }

    @PostMapping("/create")
    @CrossOrigin(origins = "http://localhost:3005")
    public ResponseEntity<Response> login(@RequestBody GroupDTO requestDTO) throws RuntimeException {
        System.out.println("requestDTO: "+requestDTO);
        requestDTO.setGroupId(null);
        System.out.println("requestDTO updated: "+requestDTO);
        GroupDTO groupDTO= groupInfoService.createGroup(requestDTO);
        log.info("groupDTO : {}", groupDTO);
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        if (groupDTO !=  null){
            responseBody = groupDTO;
            successOrFailMessage = "Group Created successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            successOrFailMessage ="Group Creation Failed.";
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }



}
