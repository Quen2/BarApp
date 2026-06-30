package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.CocktailResponseDTO;
import com.aftermidnight.aftermidnight.entities.Cocktail;

@Component
public class CocktailMapper {
    
    public CocktailResponseDTO toResponse(Cocktail cocktail) {
        return new CocktailResponseDTO(
            cocktail.getId(),
            cocktail.getCategory(),
            cocktail.getName(),
            cocktail.getIngredients(),
            cocktail.getDescription(),
            cocktail.getPosition(),
            cocktail.getCreatedAt(),
            cocktail.getSizes()
        );
    }
}
