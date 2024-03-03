package com.nus.iss.tasktracker.repository;

//import com.nus.iss.tasktracker.model.UserInfo;
import com.nus.iss.tasktracker.model.UserEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

// FIXME - TO ADD THE CODE BACK ONCE THE MODEL AND RELEVANT CODE IS WRITTEN
@Repository
public interface UserInfoRepository
//        extends JpaRepository<UserInfo, Integer>
        {
            // Implement findByUsername method to interact with the database or any data store
            UserEntity findByUsername(String username);
//
//    boolean existsByUserName(String name);
//
//    Optional<UserInfo> findByUserIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
//    List<UserInfo> findByDeletedFlagFalse();
//    @Query("SELECT COALESCE(max(a.userId),0) from UserInfo a")
//    @Transactional
//    Integer findMaxId();

}
