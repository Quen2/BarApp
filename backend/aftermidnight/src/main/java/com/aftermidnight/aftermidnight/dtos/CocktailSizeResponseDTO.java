package com.aftermidnight.aftermidnight.dtos;

import java.math.BigDecimal;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Cocktail;

public record CocktailSizeResponseDTO (
    UUID id,
    Cocktail cocktail,
    String size,
    BigDecimal price
) {}
