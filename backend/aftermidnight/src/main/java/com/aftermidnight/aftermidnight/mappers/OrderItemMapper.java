package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.OrderItemResponseDTO;
import com.aftermidnight.aftermidnight.entities.OrderItem;

@Component
public class OrderItemMapper {
    
    public OrderItemResponseDTO toResponse(OrderItem orderItem) {
        return new OrderItemResponseDTO(
            orderItem.getId(),
            orderItem.getOrder(),
            orderItem.getCocktailSize(),
            orderItem.getStatus(),
            orderItem.getQuantity(),
            orderItem.getCreatedAt()
        );
    }
}