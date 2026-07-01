package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Client;

public record  OrderResponseDTO (
    UUID id,
    Client client,
    String status,
    Integer tableNumber,
    Instant createdAt,
    Instant updatedAt,
    List<OrderItemResponseDTO> orderItems
){}
