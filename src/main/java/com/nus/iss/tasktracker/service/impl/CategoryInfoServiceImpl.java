package com.nus.iss.tasktracker.service.impl;

import com.nus.iss.tasktracker.model.CategoryInfo;
import com.nus.iss.tasktracker.repository.CategoryInfoRepository;
import com.nus.iss.tasktracker.service.CategoryInfoService;
import com.nus.iss.tasktracker.util.CrudStatus;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class CategoryInfoServiceImpl implements CategoryInfoService {

    private final CategoryInfoRepository categoryInfoRepository;

    @Autowired
    public CategoryInfoServiceImpl(CategoryInfoRepository categoryInfoRepository){ this.categoryInfoRepository=categoryInfoRepository;}


    @Override
    @Transactional
    public String createCategoryInfo(CategoryInfo categoryInfo) {
        try{
            if(!categoryInfoRepository.existsByCategoryName(categoryInfo.getCategoryName())){
                categoryInfo.setCategoryId(categoryInfoRepository.findMaxId()+1);
                categoryInfo.setCreatedDate(LocalDateTime.now());
                categoryInfoRepository.save(categoryInfo);
                return CrudStatus.RECORD_CREATED;
            }
        }catch(Exception e){
            log.debug(e.getMessage());
        }
        return CrudStatus.NO_RECORD_CREATED;
    }

    @Override
    @Transactional
    public List<CategoryInfo> readCategoryInfo() {
        return categoryInfoRepository.findByDeletedFlagFalse();
    }

    @Override
    @Transactional
    public CategoryInfo readCategoryInfo(Integer id) {
        Optional<CategoryInfo> opt = categoryInfoRepository.findByCategoryIdAndDeletedFlag(id,false);
        return opt.orElse(null);
    }

    @Override
    @Transactional
    public String updateCategoryInfo(Integer id, CategoryInfo categoryInfo) {
        Optional<CategoryInfo> opt =categoryInfoRepository.findById(id);
        if (opt.isPresent()){
            CategoryInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setCategoryName(categoryInfo.getCategoryName());
                record.setModifiedDate(LocalDateTime.now());
                categoryInfoRepository.save(record);
                return CrudStatus.RECORD_AFFECTED;
            }
        }
        return CrudStatus.NO_RECORD_AFFECTED;
    }

    @Override
    @Transactional
    public String deleteCategoryInfo(Integer id) {
        Optional<CategoryInfo> opt =categoryInfoRepository.findById(id);
        if (opt.isPresent()){
            CategoryInfo record = opt.get();
            if(!record.isDeletedFlag()) {
                record.setDeletedFlag(true);
                record.setModifiedDate(LocalDateTime.now());
                categoryInfoRepository.save(record);
                return CrudStatus.RECORD_DELETED;
            }
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

    @Override
    @Transactional
    public String hardDeleteCategoryInfo(Integer id) {
        Optional<CategoryInfo> opt =categoryInfoRepository.findById(id);
        if (opt.isPresent()){
            categoryInfoRepository.deleteById(id);
            return CrudStatus.RECORD_DELETED;
        }
        return CrudStatus.NO_RECORD_DELETED;
    }

}
