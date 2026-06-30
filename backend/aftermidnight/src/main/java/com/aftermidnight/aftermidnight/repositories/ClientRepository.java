package com.aftermidnight.aftermidnight.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.Client;

public interface ClientRepository extends JpaRepository<Client, UUID> {
    
}
