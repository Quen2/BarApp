package com.aftermidnight.aftermidnight.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.aftermidnight.aftermidnight.dtos.OrderCreateRequest;
import com.aftermidnight.aftermidnight.entities.Client;
import com.aftermidnight.aftermidnight.entities.CocktailSize;
import com.aftermidnight.aftermidnight.entities.Order;
import com.aftermidnight.aftermidnight.entities.OrderItem;
import com.aftermidnight.aftermidnight.repositories.ClientRepository;
import com.aftermidnight.aftermidnight.repositories.CocktailSizeRepository;
import com.aftermidnight.aftermidnight.repositories.OrderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {
    
    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;
    private final CocktailSizeRepository cocktailSizeRepository;
    public Order register(OrderCreateRequest request) {
        System.out.println("ITEM REQUEST = " + request.items());
System.out.println("COCKTAIL SIZE ID = " + request.items().stream().map(i -> i.cocktailSizeId()).toList());
System.out.println(request);
        Client client = clientRepository.findById(request.clientId())
        .orElseThrow(() -> new RuntimeException("Client not found"));

    Order order = new Order();
    order.setClient(client);
    order.setStatus("EN_COURS_DE_PREPARATION");
    order.setTableNumber(request.tableNumber());

    List<OrderItem> items = request.items().stream().map(itemReq -> {

        CocktailSize size = cocktailSizeRepository.findById(itemReq.cocktailSizeId())
            .orElseThrow(() -> new RuntimeException("CocktailSize not found"));

        OrderItem item = new OrderItem();
        item.setOrder(order);
        item.setCocktailSize(size);
        item.setQuantity(itemReq.quantity());
        item.setStatus("PREPARATION_INGREDIENTS");

        return item;

    }).toList();

    order.setItems(items);

    return orderRepository.save(order);
    }
    public Order getOrder(UUID id) {
        return orderRepository.findById(id).get();
    }

    public List<Order> getOrders() {
        return orderRepository.findAll();
    }
}
