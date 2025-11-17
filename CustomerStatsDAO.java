package com.departmentstore.dao;

import com.departmentstore.model.CustomerStats;
import com.departmentstore.model.User;
import com.departmentstore.util.DatabaseConnection;
import java.sql.*;
import com.departmentstore.model.Order;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerStatsDAO {
    
    public List<CustomerStats> getCustomersByRevenue(LocalDate startDate, LocalDate endDate) {
        List<CustomerStats> statsList = new ArrayList<>();
        String sql = "SELECT u.user_id, u.full_name, u.email, " +
                    "COUNT(o.order_id) as total_orders, " +
                    "SUM(o.total_amount) as total_revenue " +
                    "FROM users u " +
                    "JOIN orders o ON u.user_id = o.customer_id " +
                    "WHERE u.user_type = 'CUSTOMER' " +
                    "AND DATE(o.order_date) BETWEEN ? AND ? " +
                    "GROUP BY u.user_id, u.full_name, u.email " +
                    "ORDER BY total_revenue DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                User customer = new User();
                customer.setUserId(rs.getInt("user_id"));
                customer.setFullName(rs.getString("full_name"));
                customer.setEmail(rs.getString("email"));
                
                CustomerStats stats = new CustomerStats();
                stats.setCustomer(customer);
                stats.setTotalOrders(rs.getLong("total_orders"));
                stats.setTotalRevenue(rs.getBigDecimal("total_revenue"));
                
                statsList.add(stats);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return statsList;
    }
    
    public List<Order> getCustomerTransactions(int customerId, LocalDate startDate, LocalDate endDate) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT * FROM orders WHERE customer_id = ? AND DATE(order_date) BETWEEN ? AND ? " +
                    "ORDER BY order_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customerId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Order order = new Order();
                order.setOrderId(rs.getInt("order_id"));
                order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
                order.setTotalAmount(rs.getBigDecimal("total_amount"));
                order.setStatus(Order.OrderStatus.valueOf(rs.getString("status")));
                orders.add(order);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orders;
    }
}