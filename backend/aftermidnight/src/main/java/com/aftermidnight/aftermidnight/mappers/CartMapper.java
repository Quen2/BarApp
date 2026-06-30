package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.CartResponseDTO;
import com.aftermidnight.aftermidnight.entities.Cart;

@Component
public class CartMapper {
    
    public CartResponseDTO toResponse(Cart cart) {
        return new CartResponseDTO(
            cart.getId(),
            cart.getClient(),
            cart.getCreatedAt(),
            cart.getUpdatedAt(),
            cart.getItems()
        );
    }
}
