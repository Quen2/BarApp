package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Client;
import com.aftermidnight.aftermidnight.entities.OrderItem;

public record  OrderResponseDTO (
    UUID id,
    Client client,
    String status,
    Instant createdAt,
    Instant updatedAt,
    List<OrderItem> orderItems
){}
