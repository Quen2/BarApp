package com.aftermidnight.aftermidnight.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.CartCreateRequest;
import com.aftermidnight.aftermidnight.entities.Cart;
import com.aftermidnight.aftermidnight.entities.CartItem;
import com.aftermidnight.aftermidnight.entities.Client;
import com.aftermidnight.aftermidnight.entities.CocktailSize;
import com.aftermidnight.aftermidnight.repositories.CartRepository;
import com.aftermidnight.aftermidnight.repositories.ClientRepository;
import com.aftermidnight.aftermidnight.repositories.CocktailSizeRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class CartService {

    private final CartRepository cartRepository;
    private final ClientRepository clientRepository;
    private final CocktailSizeRepository cocktailSizeRepository;

    public Cart register(CartCreateRequest request) {

        Client client = clientRepository.findById(request.client().getId())
                .orElseThrow();

        Cart cart = new Cart();
        cart.setClient(client);

        List<CartItem> items = request.items().stream().map(i -> {

            CocktailSize size = cocktailSizeRepository.findById(i.cocktailSizeId())
                    .orElseThrow();

            CartItem item = new CartItem();
            item.setCart(cart);
            item.setCocktailSize(size);
            item.setQuantity(i.quantity());

            return item;
        }).toList();

        cart.setItems(items);

        return cartRepository.save(cart);
    }

    public Cart getCart(UUID id) {
        return cartRepository.findById(id).orElseThrow();
    }
}
