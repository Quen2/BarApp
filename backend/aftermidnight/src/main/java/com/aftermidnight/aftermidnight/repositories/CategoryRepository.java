package com.aftermidnight.aftermidnight.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aftermidnight.aftermidnight.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    
}
