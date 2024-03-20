package com.nus.iss.tasktracker.dto;

import lombok.Data;

@Data
public class GroupDTO {

    private Long groupId;
    private String groupName;
    private String groupDescription;
    private String deleteFlag;
    private String createdBy;

}
