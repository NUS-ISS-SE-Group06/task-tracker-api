package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.UserRegistrationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class UserRegistrationController {

    private UserRegistrationService userRegistrationService;

    @Autowired
    public UserRegistrationController(UserRegistrationService userRegistrationService) {
        this.userRegistrationService = userRegistrationService;
    }

    @GetMapping("user")
    public UserDTO getUserById(@RequestParam (name = "id", defaultValue = "2019") int id){
        log.info("user endpoint called with id {}", id);
        return userRegistrationService.getUserById(id);
    }



}
