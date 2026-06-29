package com.aftermidnight.aftermidnight.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "carts")
public class Cart {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "CART_ID")
    private Integer id;

    @OneToOne(mappedBy = "cart", targetEntity = Client.class, fetch=FetchType.LAZY)
    private Client client;

    @Column(name="Updated_At", nullable = false)
    private LocalDateTime updatedAt;
}
