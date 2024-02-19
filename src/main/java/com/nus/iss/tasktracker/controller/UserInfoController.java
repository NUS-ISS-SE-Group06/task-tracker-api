package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.UserInfoDTO;
import com.nus.iss.tasktracker.mapper.UserInfoMapper;
import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.service.UserInfoService;
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
@RequestMapping("/userinfo")
public class UserInfoController {
    private final UserInfoService userInfoService;
    private final UserInfoMapper userInfoMapper;
    @Autowired
    public UserInfoController(UserInfoService userInfoService, UserInfoMapper userInfoMapper){
        this.userInfoService=userInfoService;
        this.userInfoMapper=userInfoMapper;
    }

    @PostMapping
    @ApiOperation("Create a new UserInfo")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_CREATED),
            @ApiResponse(code = 400, message = CrudStatus.NO_RECORD_CREATED)
    })
    public ResponseEntity<Object> createUserInfo(@RequestBody UserInfoDTO userInfoDto){
        UserInfo userInfo=userInfoMapper.userInfoDtoToUserInfo(userInfoDto);
        String result=userInfoService.createUserInfo(userInfo);
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
    @ApiOperation("Update an existing UserInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_AFFECTED),
            @ApiResponse(code = 200, message = CrudStatus.NO_RECORD_AFFECTED)
    })
    public ResponseEntity<Object> updateUserInfo(@PathVariable Integer id, @RequestBody UserInfoDTO userInfoDto) {
        UserInfo userInfo=userInfoMapper.userInfoDtoToUserInfo(userInfoDto);
        String result=userInfoService.updateUserInfo(id,userInfo);

        String crudStatus = result.equals(CrudStatus.RECORD_AFFECTED) ? CrudStatus.RECORD_AFFECTED_JSON : CrudStatus.NO_RECORD_AFFECTED;

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(crudStatus);

    }

    @PutMapping("/pwdchange/{id}")
    @ApiOperation("Update Password an existing UserInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_AFFECTED),
            @ApiResponse(code = 200, message = CrudStatus.NO_RECORD_AFFECTED)
    })
    public ResponseEntity<Object> updateUserInfo(@PathVariable Integer id, @RequestBody String newPassword) {
        String result=userInfoService.updateUserPassword(id,newPassword);

        String crudStatus = result.equals(CrudStatus.RECORD_AFFECTED) ? CrudStatus.RECORD_AFFECTED_JSON : CrudStatus.NO_RECORD_AFFECTED;

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(crudStatus);
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an existing UserInfo")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = CrudStatus.RECORD_DELETED),
            @ApiResponse(code=200, message = CrudStatus.NO_RECORD_DELETED)
    })
    public ResponseEntity<Object> deleteUserInfo(@PathVariable Integer id) {
        String result=userInfoService.deleteUserInfo(id);

        String crudStatus = result.equals(CrudStatus.RECORD_DELETED) ? CrudStatus.RECORD_DELETED_JSON : CrudStatus.NO_RECORD_DELETED_JSON;


        return ResponseEntity.ok()
                .contentType (MediaType.APPLICATION_JSON)
                .body(crudStatus);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get UserInfo object by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of UserInfo objects")
    })
    public ResponseEntity<UserInfoDTO> readUserInfo(@PathVariable Integer id) {
        UserInfo result= userInfoService.readUserInfo(id);
        UserInfoDTO resultDto=userInfoMapper.userInfoToUserInfoDTO(result);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }

    @GetMapping
    @ApiOperation("Get all UserInfo object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of UserInfo objects")
    })
    public ResponseEntity<List<UserInfoDTO>> readUserInfo() {
        List<UserInfo> result= userInfoService.readUserInfo();
        List<UserInfoDTO> resultDto=result.stream()
                .map(userInfoMapper::userInfoToUserInfoDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }

}




