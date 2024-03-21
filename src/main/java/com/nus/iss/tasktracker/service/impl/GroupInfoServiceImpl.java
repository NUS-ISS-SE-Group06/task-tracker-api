package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.dto.GroupDTO;
import com.nus.iss.tasktracker.mapper.GroupMapper;
import com.nus.iss.tasktracker.model.GroupInfo;
import com.nus.iss.tasktracker.repository.GroupInfoRepository;
import com.nus.iss.tasktracker.service.GroupInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GroupInfoServiceImpl implements GroupInfoService {

    private  final GroupInfoRepository groupInfoRepository;
    private final GroupMapper groupMapper;

    @Autowired
    public GroupInfoServiceImpl(GroupInfoRepository groupInfoRepository, GroupMapper groupMapper) {
        this.groupInfoRepository = groupInfoRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    public GroupDTO getGroupById(int groupId) {
        log.info("getGroupById called in GroupInfoServiceImpl with id {}", groupId);

        GroupInfo groupInfo = groupInfoRepository.findByGroupId(groupId);

        log.debug("GroupInfo {}",groupInfo);
        GroupDTO groupDTO = groupMapper.groupInfoToGroupDTO(groupInfo);
        log.debug("Group DTO {}",groupDTO);
        return groupDTO;
    }

    @Override
    public GroupDTO createGroup(GroupDTO groupDTORequest) {
        log.info("createGroup called in GroupInfoServiceImpl with GroupDTO {}", groupDTORequest);

        GroupInfo groupInfoRequest = groupMapper.groupDTOToGroupInfo(groupDTORequest);
        log.debug("groupInfoRequest {}",groupInfoRequest);

        GroupInfo groupInfoResponse = groupInfoRepository.save(groupInfoRequest);
        log.debug("groupInfoResponse {}",groupInfoResponse);

        GroupDTO groupDTOResponse = groupMapper.groupInfoToGroupDTO(groupInfoResponse);
        log.debug("Group DTO {}",groupDTOResponse);
        return groupDTOResponse;
    }
}
