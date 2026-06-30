package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Order;

public interface OrderRepository extends JpaRepository<Order, UUID> {
    
}
