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
@Table(name = "order_items")
@Entity
public class OrderItem {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "OI_ID")
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Order order;

    @OneToOne(mappedBy = "orderItem", targetEntity = CocktailSize.class, fetch=FetchType.EAGER)
    private CocktailSize cocktailSize;

    @Column(name="OI_Status", nullable = false)
    private String status;

    @Column(name = "OI_QUANTITY", nullable = false)
    private Integer quantity;
}
