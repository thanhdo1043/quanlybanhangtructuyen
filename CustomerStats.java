package com.departmentstore.model;

import java.math.BigDecimal;

public class CustomerStats {
    private User customer;
    private long totalOrders;
    private BigDecimal totalRevenue;
    
    // Constructors
    public CustomerStats() {}
    
    public CustomerStats(User customer, long totalOrders, BigDecimal totalRevenue) {
        this.customer = customer;
        this.totalOrders = totalOrders;
        this.totalRevenue = totalRevenue;
    }
    
    // Getters and Setters
    public User getCustomer() { return customer; }
    public void setCustomer(User customer) { this.customer = customer; }
    
    public long getTotalOrders() { return totalOrders; }
    public void setTotalOrders(long totalOrders) { this.totalOrders = totalOrders; }
    
    public BigDecimal getTotalRevenue() { return totalRevenue; }
    public void setTotalRevenue(BigDecimal totalRevenue) { this.totalRevenue = totalRevenue; }
}