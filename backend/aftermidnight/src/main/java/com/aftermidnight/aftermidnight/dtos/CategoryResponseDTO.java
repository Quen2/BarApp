package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import com.aftermidnight.aftermidnight.entities.Barmaker;
import com.aftermidnight.aftermidnight.entities.Cocktail;

public record  CategoryResponseDTO (
    UUID id,
    Barmaker barmaker,
    String name,
    Integer position,
    Instant createdAt,
    List<Cocktail> cocktails
) {}
