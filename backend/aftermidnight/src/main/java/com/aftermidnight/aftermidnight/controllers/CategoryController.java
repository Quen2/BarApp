package com.aftermidnight.aftermidnight.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftermidnight.aftermidnight.dtos.CategoryResponseDTO;
import com.aftermidnight.aftermidnight.entities.Category;
import com.aftermidnight.aftermidnight.mappers.CategoryMapper;
import com.aftermidnight.aftermidnight.services.CategoryService;

import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryMapper categoryMapper;

    @GetMapping
    public ResponseEntity<List<Category>> getCategories () {
        return ResponseEntity.ok(categoryService.getAllCategory());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDTO> getCategory (@PathVariable UUID id) {
        Category category = categoryService.getCategory(id);
        return ResponseEntity.ok(categoryMapper.toResponse(category));
    }
    
    
}
