package com.departmentstore.service;

import com.departmentstore.dao.CustomerStatsDAO;
import com.departmentstore.model.CustomerStats;
import com.departmentstore.model.Order;
import java.time.LocalDate;
import java.util.List;

public class ReportServices {
    private CustomerStatsDAO statsDAO;
    
    public ReportServices() {
        this.statsDAO = new CustomerStatsDAO();
    }
    
    public List<CustomerStats> generateCustomerRevenueReport(LocalDate startDate, LocalDate endDate) {
        validateDateRange(startDate, endDate);
        return statsDAO.getCustomersByRevenue(startDate, endDate);
    }
    
    public List<Order> getCustomerTransactions(int customerId, LocalDate startDate, LocalDate endDate) {
        validateDateRange(startDate, endDate);
        return statsDAO.getCustomerTransactions(customerId, startDate, endDate);
    }
    
    private void validateDateRange(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null");
        }
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date cannot be after end date");
        }
    }
}