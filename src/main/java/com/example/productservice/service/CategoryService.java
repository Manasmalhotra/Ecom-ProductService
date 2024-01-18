package com.example.productservice.service;

import com.example.productservice.model.Category;

public interface CategoryService {
    Category getCategoryByName(String name);
    Category createCategory(String name);
}
