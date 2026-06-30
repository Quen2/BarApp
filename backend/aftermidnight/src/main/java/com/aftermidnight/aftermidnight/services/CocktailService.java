package com.aftermidnight.aftermidnight.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.CocktailCreateRequest;
import com.aftermidnight.aftermidnight.entities.Cocktail;
import com.aftermidnight.aftermidnight.repositories.CocktailRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CocktailService {

    private final CocktailRepository cocktailRepository;

    public Cocktail register(CocktailCreateRequest request) {
        Cocktail cocktail = new Cocktail();
        cocktail.setName(request.name());
        cocktail.setIngredients(request.ingredients());
        cocktail.setDescription(request.description());
        cocktail.setCategory(request.category());
        cocktail.setSizes(request.sizes());

        return cocktailRepository.save(cocktail);
    }

    public Cocktail getCocktail(UUID id) {
        return cocktailRepository.findById(id).get();
    }

    public List<Cocktail> getCocktails() {
        return cocktailRepository.findAll();
    }
}
