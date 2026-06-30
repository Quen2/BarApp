package com.aftermidnight.aftermidnight.dtos;

import java.math.BigDecimal;

import com.aftermidnight.aftermidnight.entities.Cocktail;

public record CocktailSizeCreateRequest (
    Cocktail cocktail,
    String size,
    BigDecimal price
) {}
