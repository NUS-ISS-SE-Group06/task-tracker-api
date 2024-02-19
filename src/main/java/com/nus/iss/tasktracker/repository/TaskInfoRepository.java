package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.TaskInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskInfoRepository extends JpaRepository<TaskInfo, Integer> {

    boolean existsByTaskName(String name);

    Optional<TaskInfo> findByTaskIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
    List<TaskInfo> findByDeletedFlagFalse();
    @Query("SELECT COALESCE(max(a.taskId),0) from TaskInfo a")
    @Transactional
    Integer findMaxId();

}
