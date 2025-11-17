package com.departmentstore.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Invoice {
    private int invoiceId;
    private Order order;
    private LocalDateTime issueDate;
    private PaymentStatus paymentStatus;
    private BigDecimal totalAmount;
    
    public enum PaymentStatus {
        PENDING, PAID, FAILED
    }
    
    // Constructors
    public Invoice() {}
    
    public Invoice(int invoiceId, Order order, LocalDateTime issueDate, PaymentStatus paymentStatus, BigDecimal totalAmount) {
        this.invoiceId = invoiceId;
        this.order = order;
        this.issueDate = issueDate;
        this.paymentStatus = paymentStatus;
        this.totalAmount = totalAmount;
    }
    
    // Getters and Setters
    public int getInvoiceId() { return invoiceId; }
    public void setInvoiceId(int invoiceId) { this.invoiceId = invoiceId; }
    
    public Order getOrder() { return order; }
    public void setOrder(Order order) { this.order = order; }
    
    public LocalDateTime getIssueDate() { return issueDate; }
    public void setIssueDate(LocalDateTime issueDate) { this.issueDate = issueDate; }
    
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public void setPaymentStatus(PaymentStatus paymentStatus) { this.paymentStatus = paymentStatus; }
    
    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}