package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.CommentInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentInfoRepository extends JpaRepository<CommentInfo, Integer> {

    Optional<CommentInfo> findByCommentIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
    List<CommentInfo> findByDeletedFlagFalse();
    @Query("SELECT COALESCE(max(a.commentId),0) from CommentInfo a")
    @Transactional
    Integer findMaxId();

}
