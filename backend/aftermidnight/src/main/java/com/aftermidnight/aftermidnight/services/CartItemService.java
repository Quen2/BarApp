package com.aftermidnight.aftermidnight.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.CartItemCreateRequest;
import com.aftermidnight.aftermidnight.entities.CartItem;
import com.aftermidnight.aftermidnight.repositories.CartItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartItemService {
    
    private final CartItemRepository cartItemRepository;

    public CartItem register(CartItemCreateRequest request) {
        CartItem cartItem = new CartItem();
        cartItem.setCart(request.cart());
        cartItem.setCocktailSize(request.cocktailSize());
        cartItem.setQuantity(request.quantity());

        return cartItemRepository.save(cartItem);
    }

    public CartItem getCartItem(UUID id) {
        return cartItemRepository.findById(id).get();
    }
}
