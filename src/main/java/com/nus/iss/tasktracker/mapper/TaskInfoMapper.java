package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.model.TaskInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaskInfoMapper {

    TaskInfoDTO taskInfoToTaskInfoDTO(TaskInfo taskInfo);
    TaskInfo taskInfoDtoToTaskInfo(TaskInfoDTO taskInfoDto);
}
