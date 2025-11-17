package com.departmentstore.controller;

import com.departmentstore.dao.CustomerStatsDAO;
import com.departmentstore.dao.UserDAO;
import com.departmentstore.model.CustomerStats;
import com.departmentstore.model.Order;
import com.departmentstore.model.User;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/customer-stats")
public class CustomerStatsController extends HttpServlet {
    private CustomerStatsDAO statsDAO;
    private UserDAO userDAO;
    
    @Override
    public void init() {
        statsDAO = new CustomerStatsDAO();
        userDAO = new UserDAO();
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        String action = request.getParameter("action");
        
        if ("viewReport".equals(action)) {
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            
            LocalDate startDate = startDateStr != null ? LocalDate.parse(startDateStr) : LocalDate.now().minusMonths(1);
            LocalDate endDate = endDateStr != null ? LocalDate.parse(endDateStr) : LocalDate.now();
            
            List<CustomerStats> customerStats = statsDAO.getCustomersByRevenue(startDate, endDate);
            request.setAttribute("customerStats", customerStats);
            request.setAttribute("startDate", startDate);
            request.setAttribute("endDate", endDate);
            
            request.getRequestDispatcher("/views/customer-stats.jsp").forward(request, response);
            
        } else if ("customerDetails".equals(action)) {
            String customerIdStr = request.getParameter("customerId");
            String startDateStr = request.getParameter("startDate");
            String endDateStr = request.getParameter("endDate");
            
            if (customerIdStr != null) {
                try {
                    int customerId = Integer.parseInt(customerIdStr);
                    LocalDate startDate = startDateStr != null ? LocalDate.parse(startDateStr) : LocalDate.now().minusMonths(1);
                    LocalDate endDate = endDateStr != null ? LocalDate.parse(endDateStr) : LocalDate.now();
                    
                    User customer = userDAO.findById(customerId);
                    List<Order> transactions = statsDAO.getCustomerTransactions(customerId, startDate, endDate);
                    
                    request.setAttribute("customer", customer);
                    request.setAttribute("transactions", transactions);
                    request.setAttribute("startDate", startDate);
                    request.setAttribute("endDate", endDate);
                    
                    request.getRequestDispatcher("/views/customer-details.jsp").forward(request, response);
                    
                } catch (NumberFormatException e) {
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid customer ID");
                }
            }
        } else {
            // Show report selection page
            request.getRequestDispatcher("/views/report-selection.jsp").forward(request, response);
        }
    }
}