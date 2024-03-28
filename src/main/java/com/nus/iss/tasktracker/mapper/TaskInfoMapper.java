package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.TaskInfoDTO;
import com.nus.iss.tasktracker.model.TaskInfo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public interface TaskInfoMapper {

    @Mapping(ignore = true, target = "createdDate")
    @Mapping(ignore = true, target = "modifiedDate")
    @Mapping(ignore = true, target = "deleteFlag")

    TaskInfo taskInfoToEntity(TaskInfoDTO requestDTO);

    @Named("toTimestamp")
    default Timestamp toTimestamp(LocalDateTime localDateTime) {
        return localDateTime != null ? Timestamp.valueOf(localDateTime) : null;
    }
    TaskInfoDTO taskInfoToTaskinfoDTO(TaskInfo savedTaskInfoEntity);


}
