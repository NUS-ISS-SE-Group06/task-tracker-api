package com.nus.iss.tasktracker.repository;
import com.nus.iss.tasktracker.model.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// FIXME - TO ADD THE CODE BACK ONCE THE MODEL AND RELEVANT CODE IS WRITTEN
@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>
//        extends JpaRepository<UserInfo, Integer>
        {
            // Implement findByUsername method to interact with the database or any data store
            UserInfo findByUsername(String username);
            UserInfo findByUsernameAndPasswordAndDeleteFlag (String username,String password,String delete_flag);
            boolean existsByUsername(String username);

//
//    boolean existsByUserName(String name);
//
//    Optional<UserInfo> findByUserIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
//    List<UserInfo> findByDeletedFlagFalse();
//    @Query("SELECT COALESCE(max(a.userId),0) from UserInfo a")
//    @Transactional
//    Integer findMaxId();

}
