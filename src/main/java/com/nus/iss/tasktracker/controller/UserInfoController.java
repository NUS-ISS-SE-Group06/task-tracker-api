package com.nus.iss.tasktracker.controller;


import com.nus.iss.tasktracker.service.UserRegistrationService;
import com.nus.iss.tasktracker.util.JWTUtil;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.util.MultiValueMap;



@RestController
@RequestMapping("/userinfo")
public class UserInfoController {

    private final JWTUtil jwtUtil;

    @Autowired
    public UserInfoController(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody MultiValueMap<String, String> formData) {
        String username = formData.getFirst("username");
        String password = formData.getFirst("password");

        String response="{ \"login\":\"success\", \"role\":\"admin\"}";

        jwtUtil.createJWT(username, "ADMIN");

        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.APPLICATION_JSON)
                .body(response);
    }

}









