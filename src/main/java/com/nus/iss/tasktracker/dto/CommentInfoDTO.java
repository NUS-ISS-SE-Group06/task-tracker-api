package com.nus.iss.tasktracker.dto;

import lombok.Data;

@Data
public class CommentInfoDTO {
    private Integer commentId;
    private Integer taskId;
    private String taskComment;
}


