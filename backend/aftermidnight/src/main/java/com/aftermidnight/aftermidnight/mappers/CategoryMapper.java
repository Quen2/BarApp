package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.CategoryResponseDTO;
import com.aftermidnight.aftermidnight.entities.Category;

@Component
public class CategoryMapper {

    public CategoryResponseDTO toResponse(Category category) {
        return new CategoryResponseDTO(
            category.getId(),
            category.getBarmaker(),
            category.getName(),
            category.getPosition(),
            category.getCreatedAt(),
            category.getCocktails()
        );
    }
}