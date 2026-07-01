package com.aftermidnight.aftermidnight.dtos;

import java.util.List;

import com.aftermidnight.aftermidnight.entities.Client;
import com.aftermidnight.aftermidnight.entities.OrderItem;

public record OrderCreateRequest(
    Client client,
    String status,
    Integer tableNumber,
    List<OrderItem> orderItems
) {}
