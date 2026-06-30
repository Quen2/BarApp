package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.aftermidnight.aftermidnight.entities.Cocktail;
import java.util.UUID;

public interface CocktailRepository extends JpaRepository<Cocktail, UUID> {
    
}
