package com.nus.iss.tasktracker.mapper;

import com.nus.iss.tasktracker.dto.CommentInfoDTO;
import com.nus.iss.tasktracker.model.CommentInfo;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CommentInfoMapper {

    CommentInfoDTO commentInfoToCommentInfoDTO(CommentInfo commentInfo);
    CommentInfo commentInfoDtoToCommentInfo(CommentInfoDTO commentInfoDto);
}
