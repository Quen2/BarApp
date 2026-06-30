package com.aftermidnight.aftermidnight.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftermidnight.aftermidnight.dtos.CocktailSizeCreateRequest;
import com.aftermidnight.aftermidnight.dtos.CocktailSizeResponseDTO;
import com.aftermidnight.aftermidnight.entities.CocktailSize;
import com.aftermidnight.aftermidnight.mappers.CocktailSizeMapper;
import com.aftermidnight.aftermidnight.services.CocktailSizeService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/cocktailsize")
@RequiredArgsConstructor
public class CocktailSizeController {
    
    private final CocktailSizeService cocktailSizeService;
    private final CocktailSizeMapper cocktailSizeMapper;

    @PostMapping
    public ResponseEntity<CocktailSizeResponseDTO> register(@Valid @RequestBody CocktailSizeCreateRequest request) {
        CocktailSize cocktailSize = cocktailSizeService.register(request);
        CocktailSizeResponseDTO response = cocktailSizeMapper.toResponse(cocktailSize);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CocktailSizeResponseDTO> getCocktailSize(@PathVariable UUID id) {
        CocktailSize cocktailSize = cocktailSizeService.getCocktailSize(id);
        return ResponseEntity.ok(cocktailSizeMapper.toResponse(cocktailSize));
    }
    
}
