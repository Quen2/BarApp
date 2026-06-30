package com.aftermidnight.aftermidnight.controllers;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aftermidnight.aftermidnight.dtos.ClientResponseDTO;
import com.aftermidnight.aftermidnight.entities.Client;
import com.aftermidnight.aftermidnight.mappers.ClientMapper;
import com.aftermidnight.aftermidnight.services.ClientService;

import lombok.RequiredArgsConstructor;

/**
 * Expose la gestion des clients "anonymes" : pas de login, juste un id
 * généré à l'arrivée sur l'app pour rattacher panier et commandes.
 */
@RestController
@RequestMapping("/api/clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;
    private final ClientMapper clientMapper;

    /**
     * Crée un nouvel identifiant client, appelé une seule fois
     * côté front au premier contact (puis stocké en local).
     */
    @PostMapping
    public ResponseEntity<ClientResponseDTO> createClient() {
        Client client = clientService.createClient();
        ClientResponseDTO response = clientMapper.toResponse(client);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientResponseDTO> getClient(@PathVariable UUID id) {
        Client client = clientService.getClient(id);
        return ResponseEntity.ok(clientMapper.toResponse(client));
    }
}