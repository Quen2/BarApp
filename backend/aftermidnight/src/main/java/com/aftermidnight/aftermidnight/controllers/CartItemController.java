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

import com.aftermidnight.aftermidnight.dtos.CartItemCreateRequest;
import com.aftermidnight.aftermidnight.dtos.CartItemResponseDTO;
import com.aftermidnight.aftermidnight.entities.CartItem;
import com.aftermidnight.aftermidnight.mappers.CartItemMapper;
import com.aftermidnight.aftermidnight.services.CartItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequestMapping("/api/cartitem")
@RequiredArgsConstructor
public class CartItemController {
    
    private final CartItemService cartItemService;
    private final CartItemMapper cartItemMapper;

    @PostMapping
    public ResponseEntity<CartItemResponseDTO> register(@Valid @RequestBody CartItemCreateRequest request) {
        CartItem cartItem = cartItemService.register(request);
        CartItemResponseDTO response = cartItemMapper.toResponse(cartItem);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CartItemResponseDTO> getCartItem(@PathVariable UUID id) {
        CartItem cartItem = cartItemService.getCartItem(id);
        return ResponseEntity.ok(cartItemMapper.toResponse(cartItem));
    }
    
}
