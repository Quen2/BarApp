package com.aftermidnight.aftermidnight.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import java.util.List;

import com.aftermidnight.aftermidnight.entities.Category;
import com.aftermidnight.aftermidnight.repositories.CategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public Category getCategory(UUID id) {
        return categoryRepository.findById(id).get();
    }

    public List<Category> getAllCategory() {
        return categoryRepository.findAll();
    }
}