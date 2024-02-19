package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.model.CommentInfo;
import com.nus.iss.tasktracker.repository.CommentInfoRepository;
import com.nus.iss.tasktracker.service.CommentInfoService;
import com.nus.iss.tasktracker.util.CrudStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CommentInfoServiceImpl implements CommentInfoService {

    private final CommentInfoRepository commentInfoRepository;

    @Autowired
    public CommentInfoServiceImpl(CommentInfoRepository commentInfoRepository){ this.commentInfoRepository=commentInfoRepository;}


    @Override
    @Transactional
    public String createCommentInfo(CommentInfo commentInfo) {
        try{
            //if( !taskInfoRepository.existByTaskId(commentInfo.getTaskId)
                commentInfo.setCommentId(commentInfoRepository.findMaxId()+1);
                commentInfo.setCreatedDate(LocalDateTime.now());
                commentInfoRepository.save(commentInfo);
                return CrudStatus.RECORD_CREATED;
            //}
        }catch(Exception e){
            log.debug(e.getMessage());
        }
        return CrudStatus.NO_RECORD_CREATED;
    }

    @Override
    @Transactional
    public List<CommentInfo> readCommentInfo() {
        return commentInfoRepository.findByDeletedFlagFalse();
    }

    @Override
    @Transactional
    public CommentInfo readCommentInfo(Integer id) {
        Optional<CommentInfo> opt = commentInfoRepository.findByCommentIdAndDeletedFlag(id,false);
        return opt.orElse(null);
    }


    @Override
    @Transactional
    public String deleteCommentInfo(Integer id) {
        Optional<CommentInfo> opt =commentInfoRepository.findById(id);
        if (opt.isPresent()){
            CommentInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setDeletedFlag(true);
                commentInfoRepository.save(record);
                return CrudStatus.RECORD_DELETED;
            }
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

    @Override
    @Transactional
    public String hardDeleteCommentInfo(Integer id) {
        Optional<CommentInfo> opt =commentInfoRepository.findById(id);
        if (opt.isPresent()){
            commentInfoRepository.deleteById(id);
            return CrudStatus.RECORD_DELETED;
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

}
