package com.aftermidnight.aftermidnight.mappers;

import org.springframework.stereotype.Component;

import com.aftermidnight.aftermidnight.dtos.BarmakerResponseDTO;
import com.aftermidnight.aftermidnight.entities.Barmaker;

@Component
public class BarmakerMapper {

    public BarmakerResponseDTO toResponse(Barmaker barmaker) {
        return new BarmakerResponseDTO(
                barmaker.getId(),
                barmaker.getName(),
                barmaker.getEmail(),
                barmaker.getCreatedAt()
        );
    }
}