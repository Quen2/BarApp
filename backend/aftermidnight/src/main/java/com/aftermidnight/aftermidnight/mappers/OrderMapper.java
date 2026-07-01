package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.OrderResponseDTO;
import com.aftermidnight.aftermidnight.entities.Order;

@Component
public class OrderMapper {

    private final OrderItemMapper orderItemMapper;

    public OrderMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    public OrderResponseDTO toResponse(Order order) {
        return new OrderResponseDTO(
            order.getId(),
            order.getClient(),
            order.getStatus(),
            order.getTableNumber(),
            order.getCreatedAt(),
            order.getUpdatedAt(),
            order.getItems()
                .stream()
                .map(orderItemMapper::toResponse)
                .toList()
        );
    }
}
