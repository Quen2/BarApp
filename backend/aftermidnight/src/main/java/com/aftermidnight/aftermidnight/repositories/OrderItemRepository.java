package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {
    
}
