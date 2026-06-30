package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.Barmaker;

public interface BarmakerRepository extends JpaRepository<Barmaker, Integer> {
    
}
