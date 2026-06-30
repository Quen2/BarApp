package com.aftermidnight.aftermidnight.dtos;

import com.aftermidnight.aftermidnight.entities.Cart;
import com.aftermidnight.aftermidnight.entities.CocktailSize;

public record CartItemCreateRequest(
    Cart cart,
    CocktailSize cocktailSize,
    Integer quantity
) {
    
}
