package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.CategoryInfo;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> {

    boolean existsByCategoryName(String name);

    Optional<CategoryInfo> findByCategoryIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
    List<CategoryInfo> findByDeletedFlagFalse();
    @Query("SELECT COALESCE(max(a.categoryId),0) from CategoryInfo a")
    @Transactional
    Integer findMaxId();

}
