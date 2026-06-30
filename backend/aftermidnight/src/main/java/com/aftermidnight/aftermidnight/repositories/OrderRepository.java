package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    
}
