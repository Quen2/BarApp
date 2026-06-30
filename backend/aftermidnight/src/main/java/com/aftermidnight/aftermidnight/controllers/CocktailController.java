package com.aftermidnight.aftermidnight.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftermidnight.aftermidnight.dtos.CocktailCreateRequest;
import com.aftermidnight.aftermidnight.dtos.CocktailResponseDTO;
import com.aftermidnight.aftermidnight.entities.Cocktail;
import com.aftermidnight.aftermidnight.mappers.CocktailMapper;
import com.aftermidnight.aftermidnight.services.CocktailService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/cocktails")
@RequiredArgsConstructor
public class CocktailController {
    
    private final CocktailService cocktailService;
    private final CocktailMapper cocktailMapper;

    @GetMapping
    public ResponseEntity<List<Cocktail>> getCocktails() {
        return ResponseEntity.ok(cocktailService.getCocktails());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CocktailResponseDTO> getCocktail (@PathVariable UUID id) {
        Cocktail cocktail = cocktailService.getCocktail(id);
        return ResponseEntity.ok(cocktailMapper.toResponse(cocktail));
    }

    @PostMapping("/create")
    public ResponseEntity<CocktailResponseDTO> create(@Valid @RequestBody CocktailCreateRequest request) {
        Cocktail cocktail = cocktailService.register(request);
        return ResponseEntity.ok(cocktailMapper.toResponse(cocktail));
    }
    
}
