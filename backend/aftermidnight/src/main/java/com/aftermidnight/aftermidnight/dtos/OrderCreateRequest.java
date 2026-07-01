package com.aftermidnight.aftermidnight.dtos;

import java.util.List;
import java.util.UUID;

public record OrderCreateRequest(
    UUID clientId,
    Integer tableNumber,
    List<OrderItemRequest> items
) {}
