package com.aftermidnight.aftermidnight.services;

import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aftermidnight.aftermidnight.entities.Client;
import com.aftermidnight.aftermidnight.repositories.ClientRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientService {

    private final ClientRepository clientRepository;

    public Client createClient() {
        Client client = new Client();
        return clientRepository.save(client);
    }

    @Transactional(readOnly = true)
    public Client getClient(UUID id) {
        return clientRepository.findById(id).get();
    }
}