package com.nox.dto;

public class OrderResponse {
    private Long orderId;
    private String productName;
    private int quantity;
    private double totalPrice;

    public OrderResponse(Long orderId, String productName, int quantity, double totalPrice) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public Long getOrderId() { return orderId; }
    public String getProductName() { return productName; }
    public int getQuantity() { return quantity; }
    public double getTotalPrice() { return totalPrice; }
}