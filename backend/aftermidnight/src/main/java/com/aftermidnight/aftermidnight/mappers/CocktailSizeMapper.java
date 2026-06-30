package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.CocktailSizeResponseDTO;
import com.aftermidnight.aftermidnight.entities.CocktailSize;

@Component
public class CocktailSizeMapper {
    public CocktailSizeResponseDTO toResponse(CocktailSize cocktailSize) {
        return new CocktailSizeResponseDTO(
            cocktailSize.getId(),
            cocktailSize.getCocktail(),
            cocktailSize.getSize(),
            cocktailSize.getPrice()
        );
    }
}
