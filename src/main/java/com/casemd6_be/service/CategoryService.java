package com.casemd6_be.service;

import com.casemd6_be.model.Category;
import com.casemd6_be.repository.ICategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    ICategoryRepo iCategoryRepo;

    public List<Category> getAllCategory(){
        return (List<Category>) iCategoryRepo.findAll();
    }
}
