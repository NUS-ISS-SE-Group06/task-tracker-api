package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.GroupInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupInfoRepository extends JpaRepository<GroupInfo, Integer> {

    public boolean existsByGroupName(String name);

    public Optional<GroupInfo> findByGroupIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
    public List<GroupInfo> findByDeletedFlagFalse();
    @Query("SELECT COALESCE(max(a.groupId),0) from GroupInfo a")
    @Transactional
    public Integer findMaxId();

}
