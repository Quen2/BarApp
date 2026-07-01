package com.aftermidnight.aftermidnight.dtos;

import java.util.UUID;

public record CartItemRequest(
    UUID cocktailSizeId,
    Integer quantity
) {}