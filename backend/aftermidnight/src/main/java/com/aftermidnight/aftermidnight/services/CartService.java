package com.aftermidnight.aftermidnight.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.CartCreateRequest;
import com.aftermidnight.aftermidnight.entities.Cart;
import com.aftermidnight.aftermidnight.repositories.CartRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {
    
    private final CartRepository cartRepository;
    
    public Cart register(CartCreateRequest request) {
        Cart cart = new Cart();
        cart.setClient(request.client());
        cart.setItems(request.items());

        return cartRepository.save(cart);
    }

    public Cart getCart(UUID id) {
        return cartRepository.findById(id).get();
    }
}
