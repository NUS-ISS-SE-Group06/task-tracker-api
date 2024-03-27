package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.TaskInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskInfoRepository extends JpaRepository<TaskInfo, Integer> {
    // Add custom query methods or override JpaRepository methods if needed

}
