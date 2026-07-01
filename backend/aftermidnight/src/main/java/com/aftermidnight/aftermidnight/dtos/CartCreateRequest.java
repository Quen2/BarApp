package com.aftermidnight.aftermidnight.dtos;

import java.util.List;

import com.aftermidnight.aftermidnight.entities.Client;

public record  CartCreateRequest (
    Client client,
    List<CartItemRequest> items
){}
