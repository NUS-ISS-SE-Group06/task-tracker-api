package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.CategoryInfoDTO;
import com.nus.iss.tasktracker.model.CategoryInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryInfoMapper {

    CategoryInfoDTO categoryInfoToCategoryInfoDTO(CategoryInfo categoryInfo);
    CategoryInfo categoryInfoDtoToCategoryInfo(CategoryInfoDTO categoryInfoDto);
}
