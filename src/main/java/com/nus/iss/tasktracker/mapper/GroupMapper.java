package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.model.GroupInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupMapper {

    GroupDTO groupInfoToGroupDTO(GroupInfo groupInfo);

}
