package com.nus.iss.tasktracker.controller;

import com.nus.iss.tasktracker.dto.CategoryInfoDTO;
import com.nus.iss.tasktracker.mapper.CategoryInfoMapper;
import com.nus.iss.tasktracker.model.CategoryInfo;
import com.nus.iss.tasktracker.service.CategoryInfoService;
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
@RequestMapping("/categoryinfo")
public class CategoryInfoController {
    private final CategoryInfoService categoryInfoService;
    private final CategoryInfoMapper categoryInfoMapper;
    @Autowired
    public CategoryInfoController(CategoryInfoService categoryInfoService, CategoryInfoMapper categoryInfoMapper){
        this.categoryInfoService=categoryInfoService;
        this.categoryInfoMapper=categoryInfoMapper;
    }

    @PostMapping
    @ApiOperation("Create a new CategoryInfo")
    @ApiResponses( value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_CREATED),
            @ApiResponse(code = 400, message = CrudStatus.NO_RECORD_CREATED)
    })
    public ResponseEntity<Object> createCategoryInfo(@RequestBody CategoryInfoDTO categoryInfoDto){
        CategoryInfo categoryInfo=categoryInfoMapper.categoryInfoDtoToCategoryInfo(categoryInfoDto);
        String result=categoryInfoService.createCategoryInfo(categoryInfo);
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
    @ApiOperation("Update an existing CategoryInfo")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = CrudStatus.RECORD_AFFECTED),
            @ApiResponse(code = 200, message = CrudStatus.NO_RECORD_AFFECTED)
    })
    public ResponseEntity<Object> updateCategoryInfo(@PathVariable Integer id, @RequestBody CategoryInfoDTO categoryInfoDto) {
        CategoryInfo categoryInfo=categoryInfoMapper.categoryInfoDtoToCategoryInfo(categoryInfoDto);
        String result=categoryInfoService.updateCategoryInfo(id,categoryInfo);

        String crudStatus = result.equals(CrudStatus.RECORD_AFFECTED) ? CrudStatus.RECORD_AFFECTED_JSON : CrudStatus.NO_RECORD_AFFECTED;

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(crudStatus);

    }

    @DeleteMapping("/{id}")
    @ApiOperation("Delete an existing CategoryInfo")
    @ApiResponses(value = {
            @ApiResponse(code=200, message = CrudStatus.RECORD_DELETED),
            @ApiResponse(code=200, message = CrudStatus.NO_RECORD_DELETED)
    })
    public ResponseEntity<Object> deleteCategoryInfo(@PathVariable Integer id) {
        String result=categoryInfoService.deleteCategoryInfo(id);

        String crudStatus = result.equals(CrudStatus.RECORD_DELETED) ? CrudStatus.RECORD_DELETED_JSON : CrudStatus.NO_RECORD_DELETED_JSON;


        return ResponseEntity.ok()
                .contentType (MediaType.APPLICATION_JSON)
                .body(crudStatus);
    }

    @GetMapping("/{id}")
    @ApiOperation("Get CategoryInfo object by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of CategoryInfo objects")
    })
    public ResponseEntity<CategoryInfoDTO> readCategoryInfo(@PathVariable Integer id) {
        CategoryInfo result= categoryInfoService.readCategoryInfo(id);
        CategoryInfoDTO resultDto=categoryInfoMapper.categoryInfoToCategoryInfoDTO(result);

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }

    @GetMapping
    @ApiOperation("Get all CategoryInfo object")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "List of CategoryInfo objects")
    })
    public ResponseEntity<List<CategoryInfoDTO>> readCategoryInfo() {
        List<CategoryInfo> result= categoryInfoService.readCategoryInfo();
        List<CategoryInfoDTO> resultDto=result.stream()
                .map(categoryInfoMapper::categoryInfoToCategoryInfoDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(resultDto);
    }




}




