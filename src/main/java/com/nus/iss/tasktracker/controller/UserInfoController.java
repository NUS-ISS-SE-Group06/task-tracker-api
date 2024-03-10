package com.nus.iss.tasktracker.controller;


import com.nus.iss.tasktracker.dto.Response;
import com.nus.iss.tasktracker.dto.UserDTO;
import com.nus.iss.tasktracker.service.UserInfoService;
import com.nus.iss.tasktracker.util.CustomResponseHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final UserInfoService userInfoService;
    @Autowired
    public UserInfoController(UserInfoService userInfoService) {
        this.userInfoService = userInfoService;
    }

    @PostMapping("/login")
    public ResponseEntity<Response> login(@RequestBody UserDTO requestDTO) throws RuntimeException {
        UserDTO userDTO= userInfoService.UserLogin(requestDTO);
        Object responseBody = null;
        HttpStatus status = HttpStatus.OK;
        String successMessage = "";

        if (userDTO !=  null){
            responseBody = userDTO;
            successMessage = "Logon successfully.";
        }

        return CustomResponseHandler.handleSuccessResponse(responseBody, status, successMessage);

    }

}









