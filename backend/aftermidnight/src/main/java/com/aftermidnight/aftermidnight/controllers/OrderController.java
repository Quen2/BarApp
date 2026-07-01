package com.aftermidnight.aftermidnight.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftermidnight.aftermidnight.dtos.OrderCreateRequest;
import com.aftermidnight.aftermidnight.dtos.OrderResponseDTO;
import com.aftermidnight.aftermidnight.entities.Order;
import com.aftermidnight.aftermidnight.mappers.OrderMapper;
import com.aftermidnight.aftermidnight.services.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {
    
    private final OrderService orderService;
    private final OrderMapper orderMapper;

    @PostMapping
    public ResponseEntity<OrderResponseDTO> register (@Valid @RequestBody OrderCreateRequest request) {
        Order order = orderService.register(request);
        OrderResponseDTO response = orderMapper.toResponse(order);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping
    public ResponseEntity<List<OrderResponseDTO>> getOrders() {
        List<OrderResponseDTO> orders = orderService.getOrders()
                .stream()
                .map(orderMapper::toResponse)
                .toList();

        return ResponseEntity.ok(orders);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderResponseDTO> getOrder (@PathVariable UUID id) {
        Order order = orderService.getOrder(id);
        return ResponseEntity.ok(orderMapper.toResponse(order));
    }
    
    
}
