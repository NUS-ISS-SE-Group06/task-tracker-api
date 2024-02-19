package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.CommentInfo;

import java.util.List;

public interface CommentInfoService {
    String createCommentInfo(CommentInfo commentInfo);
    List<CommentInfo> readCommentInfo();
    CommentInfo readCommentInfo(Integer id);
    String deleteCommentInfo(Integer id);
    String hardDeleteCommentInfo(Integer id);
}
