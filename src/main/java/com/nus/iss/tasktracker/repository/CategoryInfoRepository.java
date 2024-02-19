package com.nus.iss.tasktracker.repository;

import com.nus.iss.tasktracker.model.CategoryInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryInfoRepository extends JpaRepository<CategoryInfo, Integer> {

    public boolean existsByCategoryName(String name);

    public Optional<CategoryInfo> findByCategoryIdAndDeletedFlag(Integer id, boolean isDeletedFlag);
    public List<CategoryInfo> findByDeletedFlagFalse();
    @Query("SELECT COALESCE(max(a.categoryId),0) from CategoryInfo a")
    public Integer findMaxId();

}
