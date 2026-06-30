package com.aftermidnight.aftermidnight.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.CocktailSizeCreateRequest;
import com.aftermidnight.aftermidnight.entities.CocktailSize;
import com.aftermidnight.aftermidnight.repositories.CocktailSizeRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CocktailSizeService {
    private final CocktailSizeRepository cocktailSizeRepository;

    public CocktailSize register(CocktailSizeCreateRequest request) {
        CocktailSize cocktailSize = new CocktailSize();
        cocktailSize.setCocktail(request.cocktail());
        cocktailSize.setPrice(request.price());
        cocktailSize.setSize(request.size());

        return cocktailSizeRepository.save(cocktailSize);
    }

    public CocktailSize getCocktailSize(UUID id) {
        return cocktailSizeRepository.findById(id).get();
    }
}
