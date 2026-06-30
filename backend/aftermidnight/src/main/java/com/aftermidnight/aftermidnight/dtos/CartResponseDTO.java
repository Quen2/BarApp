package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.CartItem;
import com.aftermidnight.aftermidnight.entities.Client;

public record CartResponseDTO (
    UUID id,
    Client client,
    Instant createdAt,
    Instant updatedAt,
    List<CartItem> items
){}
