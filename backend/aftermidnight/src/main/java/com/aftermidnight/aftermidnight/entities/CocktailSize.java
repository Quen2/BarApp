package com.aftermidnight.aftermidnight.entities;

import java.math.BigDecimal;
import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cocktail_sizes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CocktailSize {

    @Id
    @GeneratedValue
    private UUID id;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "cocktail_id", nullable = false)
    private Cocktail cocktail;

    @Column(nullable = false, length = 1)
    private String size;

    @Column(nullable = false, precision = 6, scale = 2)
    private BigDecimal price;
}