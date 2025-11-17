package com.departmentstore.model;

import java.math.BigDecimal;

public class ImportDetail {
    private int importDetailId;
    private Import importRecord;
    private Product product;
    private int quantity;
    private BigDecimal unitCost;
    
    // Constructors
    public ImportDetail() {}
    
    public ImportDetail(int importDetailId, Product product, int quantity, BigDecimal unitCost) {
        this.importDetailId = importDetailId;
        this.product = product;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }
    
    // Getters and Setters
    public int getImportDetailId() { return importDetailId; }
    public void setImportDetailId(int importDetailId) { this.importDetailId = importDetailId; }
    
    public Import getImportRecord() { return importRecord; }
    public void setImportRecord(Import importRecord) { this.importRecord = importRecord; }
    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }
    
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    
    public BigDecimal getUnitCost() { return unitCost; }
    public void setUnitCost(BigDecimal unitCost) { this.unitCost = unitCost; }
    
    // Utility method
    public BigDecimal getSubTotal() {
        return unitCost.multiply(BigDecimal.valueOf(quantity));
    }
}