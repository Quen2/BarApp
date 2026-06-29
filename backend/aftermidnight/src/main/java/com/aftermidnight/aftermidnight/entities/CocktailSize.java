package com.aftermidnight.aftermidnight.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cocktail_sizes")
public class CocktailSize {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CS_ID")
    private Integer id;

    @OneToOne(mappedBy = "cocktailSize", targetEntity = Cocktail.class)
    private Cocktail cocktail;

    @Column(name = "CS_Size", nullable = false)
    private String size;

    @Column(name = "CS_Price", nullable = false)
    private Double price;
}