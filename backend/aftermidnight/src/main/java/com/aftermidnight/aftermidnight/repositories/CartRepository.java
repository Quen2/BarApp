package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Cart;

public interface CartRepository extends JpaRepository<Cart, UUID> {
    
}
