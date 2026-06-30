package com.aftermidnight.aftermidnight.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.OrderCreateRequest;
import com.aftermidnight.aftermidnight.entities.Order;
import com.aftermidnight.aftermidnight.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;

    public Order register(OrderCreateRequest request) {
        Order order = new Order();
        order.setClient(request.client());
        order.setItems(request.orderItems());
        order.setStatus(request.status());

        return orderRepository.save(order);
    }

    public Order getOrder(UUID id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
