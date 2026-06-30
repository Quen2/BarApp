package com.aftermidnight.aftermidnight.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftermidnight.aftermidnight.dtos.CartCreateRequest;
import com.aftermidnight.aftermidnight.dtos.CartResponseDTO;
import com.aftermidnight.aftermidnight.entities.Cart;
import com.aftermidnight.aftermidnight.mappers.CartMapper;
import com.aftermidnight.aftermidnight.services.CartService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/cart")
@RequiredArgsConstructor
public class CartController {
    
    private final CartService cartService;
    private final CartMapper cartMapper;

    @PostMapping
    public ResponseEntity<CartResponseDTO> register(@Valid @RequestBody CartCreateRequest request) {
        Cart cart = cartService.register(request);
        CartResponseDTO response = cartMapper.toResponse(cart);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartResponseDTO> getCart(@PathVariable UUID id) {
        Cart cart = cartService.getCart(id);
        return ResponseEntity.ok(cartMapper.toResponse(cart));
    }
    
}
