package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Category;
import com.aftermidnight.aftermidnight.entities.CocktailSize;

public record CocktailResponseDTO (
    UUID id,
    Category category,
    String name,
    String ingredients,
    String description,
    Integer position,
    Instant createdAt,
    List<CocktailSize> sizes
) {}
