package com.nus.iss.tasktracker.service;

import com.nus.iss.tasktracker.model.CategoryInfo;

import java.util.List;

public interface CategoryInfoService {
    public String createCategoryInfo(CategoryInfo categoryInfo);
    public List<CategoryInfo> readCategoryInfo();
    public CategoryInfo readCategoryInfo(Integer id);
    public String updateCategoryInfo(Integer id, CategoryInfo categoryInfo);
    public String deleteCategoryInfo(Integer id);
    public String hardDeleteCategoryInfo(Integer id);
}
