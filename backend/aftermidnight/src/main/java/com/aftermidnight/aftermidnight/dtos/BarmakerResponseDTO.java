package com.aftermidnight.aftermidnight.dtos;

import java.time.Instant;
import java.util.UUID;

/**
 * Représentation publique d'un barmaker : ne contient jamais
 * le mot de passe (même haché), pour ne jamais le renvoyer en JSON.
 */
public record BarmakerResponseDTO(
        UUID id,
        String name,
        String email,
        Instant createdAt
) {
}