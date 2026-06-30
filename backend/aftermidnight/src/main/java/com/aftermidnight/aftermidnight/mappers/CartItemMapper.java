package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.CartItemResponseDTO;
import com.aftermidnight.aftermidnight.entities.CartItem;

@Component
public class CartItemMapper {
    
    public CartItemResponseDTO toResponse(CartItem cartItem) {
        return new CartItemResponseDTO(
            cartItem.getId(),
            cartItem.getCart(),
            cartItem.getCocktailSize(),
            cartItem.getQuantity()
        );
    }
}
