package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aftermidnight.aftermidnight.entities.Cocktail;

public interface CocktailRepository extends JpaRepository<Cocktail, Integer> {
    
}
