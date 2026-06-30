package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
    
}
