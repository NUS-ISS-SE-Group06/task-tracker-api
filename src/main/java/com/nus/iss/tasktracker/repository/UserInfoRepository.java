package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer> {

    public boolean existsByUserName(String name);

    public Optional<UserInfo> findByUserIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
    public List<UserInfo> findByDeletedFlagFalse();
    @Query("SELECT COALESCE(max(a.userId),0) from UserInfo a")
    public Integer findMaxId();

}
