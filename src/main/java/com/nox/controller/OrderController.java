package com.nox.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.*;

import com.nox.dto.OrderResponse;
import com.nox.exception.ResourceNotFoundException;
import com.nox.model.Order;
import com.nox.model.Product;
import com.nox.model.User;
import com.nox.repository.UserRepository;
import com.nox.service.OrderService;
import com.nox.service.ProductService;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;
    private final ProductService productService;
    private final UserRepository userRepository;

    public OrderController(OrderService orderService, ProductService productService, UserRepository userRepository) {
        this.orderService = orderService;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderResponse>> getOrders() {
        List<OrderResponse> response = orderService.getOrders()
        .stream()
        .map(order -> new OrderResponse(
                order.getId(),
                order.getProduct().getName(),
                order.getQuantity(),
                order.getTotalPrice()
        ))
        .collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    @GetMapping("/orders/total")
    public double getGrandTotal() {
        return orderService.calculateGrandTotal();
    }

    @PostMapping("/orders")
    public Object createOrder(@RequestBody CreateOrderRequest request) {
        Product product = productService.getProductById(request.getProductId());

        if (product == null) {
            throw new ResourceNotFoundException("Product not found");
        }

        if (request.getQuantity() <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }

        User user = userRepository.findById(1)
    .orElseThrow(() -> new RuntimeException("User not found"));
        
        Order savedOrder = orderService.createOrder(user, product, request.getQuantity());
        
        OrderResponse response = new OrderResponse(
                savedOrder.getId(),
                savedOrder.getProduct().getName(),
                savedOrder.getQuantity(),
                savedOrder.getTotalPrice()
        );
        
        return ResponseEntity.ok(response);
    }

    public static class CreateOrderRequest {
        private int productId;
        private int quantity;

        public int getProductId() {
            return productId;
        }

        public void setProductId(int productId) {
            this.productId = productId;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }
    }
}