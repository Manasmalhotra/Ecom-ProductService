package com.example.productservice.service;

import com.example.productservice.model.Category;
import com.example.productservice.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService{
    CategoryRepository categoryRepository;
    public CategoryServiceImpl(CategoryRepository categoryRepository){
        this.categoryRepository=categoryRepository;
    }
    @Override
    public Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByCategoryName((name));
    }

    @Override
    public Category createCategory(String name) {
        Category category=new Category();
        category.setCategoryName(name);
        return categoryRepository.save(category);
    }
}
