package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.GroupInfoDTO;
import com.nus.iss.tasktracker.model.GroupInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface GroupInfoMapper {

    GroupInfoDTO groupInfoToGroupInfoDTO(GroupInfo groupInfo);
    GroupInfo groupInfoDtoToGroupInfo(GroupInfoDTO groupInfoDto);
}
