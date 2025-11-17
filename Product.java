package com.departmentstore.model;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String name;
    private String description;
    private String category;
    private BigDecimal price;
    private int quantityInStock;
    private String imageUrl;
    private Supplier supplier;
    private boolean isActive;
    
    // Constructors
    public Product() {}
    
    public Product(int productId, String name, String description, BigDecimal price, int quantityInStock) {
        this.productId = productId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }
    
    // Getters and Setters
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    
    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }
    
    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }
    
    public int getQuantityInStock() { return quantityInStock; }
    public void setQuantityInStock(int quantityInStock) { this.quantityInStock = quantityInStock; }
    
    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
    
    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
    
    public boolean isActive() { return isActive; }
    public void setActive(boolean active) { isActive = active; }
}