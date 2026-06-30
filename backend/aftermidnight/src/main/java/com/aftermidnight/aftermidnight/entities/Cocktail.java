package com.aftermidnight.aftermidnight.entities;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cocktails")
public class Cocktail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CO_ID")
    private Integer id;

    @Column(name = "CO_NAME", nullable = false)
    private String name;

    @Column(name = "CO_Ingredients", columnDefinition = "TEXT")
    private String ingredients;

    @Column(name = "CO_PRICE", nullable = false)
    private Double price;

    @OneToOne(mappedBy = "cocktail", targetEntity = Category.class, fetch=FetchType.LAZY)
    private Category category;

    @OneToMany(mappedBy = "cocktail", targetEntity = CocktailSize.class, fetch=FetchType.LAZY)
    private List<CocktailSize> cocktailSizes;
}