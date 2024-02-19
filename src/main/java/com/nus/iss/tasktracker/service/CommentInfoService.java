package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.CommentInfo;

import java.util.List;

public interface CommentInfoService {
    public String createCommentInfo(CommentInfo commentInfo);
    public List<CommentInfo> readCommentInfo();
    public CommentInfo readCommentInfo(Integer id);
    public String deleteCommentInfo(Integer id);
    public String hardDeleteCommentInfo(Integer id);
}
