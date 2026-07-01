package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.OrderItemResponseDTO;
import com.aftermidnight.aftermidnight.entities.OrderItem;

@Component
public class OrderItemMapper {

    private final CocktailSizeMapper cocktailSizeMapper;

    public OrderItemMapper(CocktailSizeMapper cocktailSizeMapper) {
        this.cocktailSizeMapper = cocktailSizeMapper;
    }

    public OrderItemResponseDTO toResponse(OrderItem orderItem) {
        return new OrderItemResponseDTO(
            orderItem.getId(),
            cocktailSizeMapper.toResponse(orderItem.getCocktailSize()),
            orderItem.getStatus(),
            orderItem.getQuantity(),
            orderItem.getCreatedAt()
        );
    }
}