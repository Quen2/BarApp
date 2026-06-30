package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.CocktailSize;

public interface CocktailSizeRepository extends JpaRepository<CocktailSize, Integer> {
    
}
