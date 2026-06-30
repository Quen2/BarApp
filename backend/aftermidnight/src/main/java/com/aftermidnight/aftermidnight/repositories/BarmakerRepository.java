package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;
import java.util.Optional;
import com.aftermidnight.aftermidnight.entities.Barmaker;

public interface BarmakerRepository extends JpaRepository<Barmaker, UUID> {
    Optional<Barmaker> findByEmail(String email);
 
    boolean existsByEmail(String email);
}
