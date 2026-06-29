package com.aftermidnight.aftermidnight.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Table(name = "cart_items")
@Entity
public class CartItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CI_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cart cart;

    @OneToOne(mappedBy = "cartItem", targetEntity = CocktailSize.class, fetch=FetchType.EAGER)
    private CocktailSize cocktailSize;

    @Column(name = "CI_QUANTITY", nullable = false)
    private Integer quantity;
}
