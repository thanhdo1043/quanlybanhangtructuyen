package com.departmentstore.model;

import java.math.BigDecimal;

public class OrderDetail {
    private int orderDetailId;
    private Order order;
    private Product product;
    private int quantity;
    private BigDecimal unitPrice;
    
    // Constructors
    public OrderDetail() {}
    
    public OrderDetail(int orderDetailId, Product product, int quantity, BigDecimal unitPrice) {
        this.orderDetailId = orderDetailId;
        this.product = product;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
    }
    
    // Getters and Setters
    public int getOrderDetailId() { return orderDetailId; }
    public void setOrderDetailId(int orderDetailId) { this.orderDetailId = orderDetailId; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public BigDecimal getUnitPrice() { return unitPrice; }
    public void setUnitPrice(BigDecimal unitPrice) { this.unitPrice = unitPrice; }
    
    // Utility method
    public BigDecimal getSubTotal() {
        return unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}