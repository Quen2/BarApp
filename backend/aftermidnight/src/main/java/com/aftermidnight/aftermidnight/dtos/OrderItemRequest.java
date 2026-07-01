package com.aftermidnight.aftermidnight.dtos;

import java.util.UUID;

public record OrderItemRequest(
    UUID cocktailSizeId,
    Integer quantity
) {}