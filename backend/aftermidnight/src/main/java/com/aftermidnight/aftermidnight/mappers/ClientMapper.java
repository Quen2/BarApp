package com.aftermidnight.aftermidnight.mappers;

import com.aftermidnight.aftermidnight.dtos.ClientResponseDTO;
import com.aftermidnight.aftermidnight.entities.Client;
import org.springframework.stereotype.Component;

@Component
public class ClientMapper {
 
    public ClientResponseDTO toResponse(Client client) {
        return new ClientResponseDTO(client.getId(), client.getCreatedAt());
    }
}