package com.departmentstore.service;

import com.departmentstore.dao.ProductDAO;
import com.departmentstore.model.Product;
import java.util.List;

public class ProductService {
    private ProductDAO productDAO;
    
    public ProductService() {
        this.productDAO = new ProductDAO();
    }
    
    public List<Product> searchProducts(String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            throw new IllegalArgumentException("Search keyword cannot be empty");
        }
        return productDAO.findByName(keyword.trim());
    }
    
    public Product getProductDetails(int productId) {
        if (productId <= 0) {
            throw new IllegalArgumentException("Invalid product ID");
        }
        return productDAO.findById(productId);
    }
    
    public List<Product> getAllActiveProducts() {
        // This could be implemented in ProductDAO
        return productDAO.findByName(""); // Temporary implementation
    }
}