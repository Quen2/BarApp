package com.aftermidnight.aftermidnight.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftermidnight.aftermidnight.dtos.OrderItemCreateRequest;
import com.aftermidnight.aftermidnight.dtos.OrderItemResponseDTO;
import com.aftermidnight.aftermidnight.entities.OrderItem;
import com.aftermidnight.aftermidnight.mappers.OrderItemMapper;
import com.aftermidnight.aftermidnight.services.OrderItemService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping("/api/orderitems")
@RequiredArgsConstructor
public class OrderItemController {
    
    private final OrderItemService orderItemService;
    private final OrderItemMapper orderItemMapper;

    @PostMapping
    public ResponseEntity<OrderItemResponseDTO> register(@Valid @RequestBody OrderItemCreateRequest request) {
        OrderItem orderItem = orderItemService.register(request);
        OrderItemResponseDTO response = orderItemMapper.toResponse(orderItem);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItemResponseDTO> getOrderItems(@PathVariable UUID id) {
        OrderItem orderItem = orderItemService.getOrderItem(id);
        return ResponseEntity.ok(orderItemMapper.toResponse(orderItem));
    }
    
    @PatchMapping("/{id}/next")
    public OrderItem nextStep(@PathVariable UUID id) {
        return orderItemService.nextStep(id);
    }
    
}
