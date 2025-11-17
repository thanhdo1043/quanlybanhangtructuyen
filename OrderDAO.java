package com.departmentstore.dao;

import com.departmentstore.model.Order;
import com.departmentstore.model.OrderDetail;
import com.departmentstore.model.User;
import com.departmentstore.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {
    
    public Order findById(int orderId) {
        Order order = null;
        String sql = "SELECT o.*, u.full_name, u.email " +
                    "FROM orders o " +
                    "JOIN users u ON o.customer_id = u.user_id " +
                    "WHERE o.order_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                order = mapResultSetToOrder(rs);
                order.setOrderDetails(getOrderDetails(orderId));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return order;
    }
    
    public List<Order> findByCustomerIdAndDate(int customerId, java.time.LocalDate startDate, java.time.LocalDate endDate) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.full_name, u.email " +
                    "FROM orders o " +
                    "JOIN users u ON o.customer_id = u.user_id " +
                    "WHERE o.customer_id = ? AND DATE(o.order_date) BETWEEN ? AND ? " +
                    "ORDER BY o.order_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, customerId);
            stmt.setDate(2, Date.valueOf(startDate));
            stmt.setDate(3, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orders;
    }
    
    public List<Order> findOrdersBetweenDates(java.time.LocalDate startDate, java.time.LocalDate endDate) {
        List<Order> orders = new ArrayList<>();
        String sql = "SELECT o.*, u.full_name, u.email " +
                    "FROM orders o " +
                    "JOIN users u ON o.customer_id = u.user_id " +
                    "WHERE DATE(o.order_date) BETWEEN ? AND ? " +
                    "ORDER BY o.order_date DESC";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setDate(1, Date.valueOf(startDate));
            stmt.setDate(2, Date.valueOf(endDate));
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                orders.add(mapResultSetToOrder(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return orders;
    }
    
    private Order mapResultSetToOrder(ResultSet rs) throws SQLException {
        Order order = new Order();
        order.setOrderId(rs.getInt("order_id"));
        
        // Create customer object
        User customer = new User();
        customer.setUserId(rs.getInt("customer_id"));
        customer.setFullName(rs.getString("full_name"));
        customer.setEmail(rs.getString("email"));
        order.setCustomer(customer);
        
        order.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());
        order.setTotalAmount(rs.getBigDecimal("total_amount"));
        order.setStatus(Order.OrderStatus.valueOf(rs.getString("status")));
        
        return order;
    }
    
    private List<OrderDetail> getOrderDetails(int orderId) {
        List<OrderDetail> details = new ArrayList<>();
        String sql = "SELECT od.*, p.name as product_name, p.description " +
                    "FROM order_details od " +
                    "JOIN products p ON od.product_id = p.product_id " +
                    "WHERE od.order_id = ?";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, orderId);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                OrderDetail detail = new OrderDetail();
                detail.setOrderDetailId(rs.getInt("order_detail_id"));
                detail.setQuantity(rs.getInt("quantity"));
                detail.setUnitPrice(rs.getBigDecimal("unit_price"));
                
                // Create product object
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setDescription(rs.getString("description"));
                detail.setProduct(product);
                
                details.add(detail);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return details;
    }
}