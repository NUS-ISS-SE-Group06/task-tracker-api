package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.CommentInfoDTO;
import com.nus.iss.tasktracker.mapper.CommentInfoMapper;
import com.nus.iss.tasktracker.model.CommentInfo;
import com.nus.iss.tasktracker.service.CommentInfoService;
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
@RequestMapping("/commentinfo")
public class CommentInfoController {
    private final CommentInfoService commentInfoService;
    private final CommentInfoMapper commentInfoMapper;
    @Autowired
    public CommentInfoController(CommentInfoService commentInfoService, CommentInfoMapper commentInfoMapper){
        this.commentInfoService=commentInfoService;
        this.commentInfoMapper=commentInfoMapper;
    }

    @PostMapping
    @ApiOperation("Create a new GroupInfo")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_CREATED),
            @ApiResponse(code = 400, message = CrudStatus.NO_RECORD_CREATED)
    })
    public ResponseEntity<Object> createCommentInfo(@RequestBody CommentInfoDTO commentInfoDto){
        CommentInfo commentInfo=commentInfoMapper.commentInfoDtoToCommentInfo(commentInfoDto);
        String result=commentInfoService.createCommentInfo(commentInfo);
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


    @DeleteMapping("/{id}")
    @ApiOperation("Delete an existing GroupInfo")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = CrudStatus.RECORD_DELETED),
            @ApiResponse(code=200, message = CrudStatus.NO_RECORD_DELETED)
    })
    public ResponseEntity<Object> deleteCommentInfo(@PathVariable Integer id) {
        String result=commentInfoService.deleteCommentInfo(id);

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
    public ResponseEntity<CommentInfoDTO> readCommentInfo(@PathVariable Integer id) {
        CommentInfo result= commentInfoService.readCommentInfo(id);
        CommentInfoDTO resultDto=commentInfoMapper.commentInfoToCommentInfoDTO(result);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }

    @GetMapping
    @ApiOperation("Get all GroupInfo object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of GroupInfo objects")
    })
    public ResponseEntity<List<CommentInfoDTO>> readCommentInfo() {
        List<CommentInfo> result= commentInfoService.readCommentInfo();
        List<CommentInfoDTO> resultDto=result.stream()
                .map(commentInfoMapper::commentInfoToCommentInfoDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }




}




