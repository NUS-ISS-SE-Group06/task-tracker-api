package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.CategoryInfo;

import java.util.List;

public interface CategoryInfoService {
    String createCategoryInfo(CategoryInfo categoryInfo);
    List<CategoryInfo> readCategoryInfo();
    CategoryInfo readCategoryInfo(Integer id);
    String updateCategoryInfo(Integer id, CategoryInfo categoryInfo);
    String deleteCategoryInfo(Integer id);
    String hardDeleteCategoryInfo(Integer id);
}
