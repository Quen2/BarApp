package com.aftermidnight.aftermidnight.dtos;

import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Cart;
import com.aftermidnight.aftermidnight.entities.CocktailSize;

public record CartItemResponseDTO(
    UUID id,
    Cart cart,
    CocktailSize cocktailSize,
    Integer quantity
) {
    
}
