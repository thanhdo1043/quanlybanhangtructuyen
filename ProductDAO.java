package com.departmentstore.dao;

import com.departmentstore.model.Product;
import com.departmentstore.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    
    public List<Product> findByName(String keyword) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products WHERE name LIKE ? AND is_active = TRUE";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setString(1, "%" + keyword + "%");
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                products.add(mapResultSetToProduct(rs));
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return products;
    }
    
    public Product findById(int productId) {
        Product product = null;
        String sql = "SELECT p.*, s.supplier_name FROM products p " +
                    "LEFT JOIN suppliers s ON p.supplier_id = s.supplier_id " +
                    "WHERE p.product_id = ? AND p.is_active = TRUE";
        
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                product = mapResultSetToProduct(rs);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return product;
    }
    
    private Product mapResultSetToProduct(ResultSet rs) throws SQLException {
        Product product = new Product();
        product.setProductId(rs.getInt("product_id"));
        product.setName(rs.getString("name"));
        product.setDescription(rs.getString("description"));
        product.setCategory(rs.getString("category"));
        product.setPrice(rs.getBigDecimal("price"));
        product.setQuantityInStock(rs.getInt("quantity_in_stock"));
        product.setImageUrl(rs.getString("image_url"));
        product.setActive(rs.getBoolean("is_active"));
        return product;
    }
}