package com.aftermidnight.aftermidnight.dtos;

import java.util.List;

import com.aftermidnight.aftermidnight.entities.Category;
import com.aftermidnight.aftermidnight.entities.CocktailSize;

public record CocktailCreateRequest (
    Category category,
    String name,
    String ingredients,
    String description,
    List<CocktailSize> sizes
) {}
