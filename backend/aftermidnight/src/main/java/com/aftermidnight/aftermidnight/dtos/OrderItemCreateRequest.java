package com.aftermidnight.aftermidnight.dtos;

import com.aftermidnight.aftermidnight.entities.CocktailSize;
import com.aftermidnight.aftermidnight.entities.Order;

public record  OrderItemCreateRequest (
    Order order,
    CocktailSize cocktailSize, 
    String status,
    Integer quantity
){}
