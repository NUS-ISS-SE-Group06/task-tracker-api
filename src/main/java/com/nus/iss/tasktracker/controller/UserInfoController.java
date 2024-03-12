package com.nus.iss.tasktracker.controller;


import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.UserInfoService;
import com.nus.iss.tasktracker.util.CustomResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;


@RestController
@RequestMapping("/userinfo")
@Slf4j
public class UserInfoController {

    private final UserInfoService userInfoService;
    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserDTO requestDTO) throws RuntimeException {
        UserDTO userDTO= userInfoService.UserLogin(requestDTO);
        log.info("userDTO : {}", userDTO);
        Object responseBody=null;
        HttpStatus status = HttpStatus.OK;
        String successOrFailMessage="";

        if (userDTO !=  null){
            responseBody = userDTO;
            successOrFailMessage = "Logon successfully.";
            return CustomResponseHandler.handleSuccessResponse(responseBody, status, successOrFailMessage);
        } else {
            successOrFailMessage ="Invalid Credential.";
            return CustomResponseHandler.handleFailResponse(responseBody, status, successOrFailMessage);
        }
    }

}









