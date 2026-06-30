package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.CartItem;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    
}
