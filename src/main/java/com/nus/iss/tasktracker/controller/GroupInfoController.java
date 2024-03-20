package com.nus.iss.tasktracker.controller;


import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.service.GroupInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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


}
