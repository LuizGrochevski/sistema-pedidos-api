package com.nox.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.nox.model.Order;
import com.nox.model.Product;
import com.nox.model.User;
import com.nox.repository.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository repository;

    public OrderService(OrderRepository repository) {
        this.repository = repository;
    }

    public Order createOrder(User user, Product product, int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Invalid quantity");
        }

        Order order = new Order(user, product, quantity);
        return repository.save(order);
    }

    public List<Order> getOrders() {
        return repository.findAll();
    }

    public double calculateGrandTotal() {
        return repository.findAll()
                .stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }
}