package com.departmentstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class Import {
    private int importId;
    private LocalDateTime importDate;
    private Supplier supplier;
    private BigDecimal totalCost;
    private List<ImportDetail> importDetails;
    
    // Constructors
    public Import() {}
    
    public Import(int importId, LocalDateTime importDate, Supplier supplier, BigDecimal totalCost) {
        this.importId = importId;
        this.importDate = importDate;
        this.supplier = supplier;
        this.totalCost = totalCost;
    }
    
    // Getters and Setters
    public int getImportId() { return importId; }
    public void setImportId(int importId) { this.importId = importId; }
    
    public LocalDateTime getImportDate() { return importDate; }
    public void setImportDate(LocalDateTime importDate) { this.importDate = importDate; }
    
    public Supplier getSupplier() { return supplier; }
    public void setSupplier(Supplier supplier) { this.supplier = supplier; }
    
    public BigDecimal getTotalCost() { return totalCost; }
    public void setTotalCost(BigDecimal totalCost) { this.totalCost = totalCost; }
    
    public List<ImportDetail> getImportDetails() { return importDetails; }
    public void setImportDetails(List<ImportDetail> importDetails) { this.importDetails = importDetails; }
}