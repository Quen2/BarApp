package com.aftermidnight.aftermidnight.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.OrderItemCreateRequest;
import com.aftermidnight.aftermidnight.entities.OrderItem;
import com.aftermidnight.aftermidnight.repositories.OrderItemRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {
    
    private final OrderItemRepository orderItemRepository;

    public OrderItem register(OrderItemCreateRequest request) {
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(request.order());
        orderItem.setCocktailSize(request.cocktailSize());
        orderItem.setStatus(request.status());
        orderItem.setQuantity(request.quantity());
        
        return orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItem(UUID id) {
        return orderItemRepository.findById(id).get();
    }

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }
}
