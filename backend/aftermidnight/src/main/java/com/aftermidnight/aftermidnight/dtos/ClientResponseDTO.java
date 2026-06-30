package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.UUID;

public record ClientResponseDTO(
        UUID id,
        Instant createdAt
) {
}