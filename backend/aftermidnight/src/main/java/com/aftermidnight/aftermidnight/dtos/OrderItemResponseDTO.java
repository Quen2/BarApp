package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.CocktailSize;
import com.aftermidnight.aftermidnight.entities.Order;


public record OrderItemResponseDTO (
    UUID id,
    Order order,
    CocktailSize cocktailSize,
    String status,
    Integer quantity,
    Instant createdAt
){}
