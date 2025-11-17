package com.departmentstore.controller;

import com.departmentstore.dao.ProductDAO;
import com.departmentstore.model.Product;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/search")
public class SearchItemController extends HttpServlet {
    private ProductDAO productDAO;
    
    @Override
    public void init() {
        productDAO = new ProductDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        String keyword = request.getParameter("keyword");
        
        if ("search".equals(action) && keyword != null && !keyword.trim().isEmpty()) {
            List<Product> products = productDAO.findByName(keyword.trim());
            request.setAttribute("products", products);
            request.setAttribute("searchKeyword", keyword);
        }
        
        if ("details".equals(action)) {
            String productIdStr = request.getParameter("productId");
            if (productIdStr != null) {
                try {
                    int productId = Integer.parseInt(productIdStr);
                    Product product = productDAO.findById(productId);
                    request.setAttribute("product", product);
                    request.getRequestDispatcher("/views/product-details.jsp").forward(request, response);
                    return;
                } catch (NumberFormatException e) {
                    // Handle invalid product ID
                }
            }
        }
        
        request.getRequestDispatcher("/views/search-items.jsp").forward(request, response);
    }
}