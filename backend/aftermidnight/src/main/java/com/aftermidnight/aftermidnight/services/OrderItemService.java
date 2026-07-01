package com.aftermidnight.aftermidnight.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.OrderItemCreateRequest;
import com.aftermidnight.aftermidnight.entities.CocktailSize;
import com.aftermidnight.aftermidnight.entities.Order;
import com.aftermidnight.aftermidnight.entities.OrderItem;
import com.aftermidnight.aftermidnight.repositories.CocktailSizeRepository;
import com.aftermidnight.aftermidnight.repositories.OrderItemRepository;
import com.aftermidnight.aftermidnight.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final CocktailSizeRepository cocktailSizeRepository;

    public OrderItem register(OrderItemCreateRequest request) {

        Order order = orderRepository.findById(request.orderId())
            .orElseThrow(() -> new RuntimeException("Order not found"));

        CocktailSize cocktailSize = cocktailSizeRepository.findById(request.cocktailSizeId())
            .orElseThrow(() -> new RuntimeException("CocktailSize not found"));

        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setCocktailSize(cocktailSize);
        orderItem.setStatus(request.status());
        orderItem.setQuantity(request.quantity());

        return orderItemRepository.save(orderItem);
    }

    public OrderItem getOrderItem(UUID id) {
        return orderItemRepository.findById(id).orElseThrow();
    }

    public List<OrderItem> getOrderItems() {
        return orderItemRepository.findAll();
    }
}