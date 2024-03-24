package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.TaskDTO;
import com.nus.iss.tasktracker.model.TaskInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface TaskMapper {

    TaskDTO taskInfoToTaskDTO(TaskInfo taskInfo);

    @Mapping(ignore = true, target = "createdDate")
    @Mapping(ignore = true, target = "modifiedDate")
    @Mapping(ignore = true, target = "deleteFlag")
    TaskInfo taskDTOToTaskInfo(TaskDTO taskDTO);

}
