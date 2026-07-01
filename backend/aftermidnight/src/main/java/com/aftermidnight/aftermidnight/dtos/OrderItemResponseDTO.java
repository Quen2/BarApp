package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.UUID;


public record OrderItemResponseDTO (
    UUID id,
    CocktailSizeResponseDTO cocktailSize,
    String status,
    Integer quantity,
    Instant createdAt
){}
