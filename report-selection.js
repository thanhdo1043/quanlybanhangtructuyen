<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Report Selection</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Management Dashboard</h1>
            <nav>
                <a href="${pageContext.request.contextPath}/">Home</a>
                <a href="${pageContext.request.contextPath}/customer-stats">Customer Statistics</a>
            </nav>
        </header>

        <main>
            <h2>Select Report Type</h2>
            
            <div class="report-options">
                <div class="report-card">
                    <h3>Customer Statistics by Revenue</h3>
                    <p>View customer rankings based on revenue generated within specific time periods</p>
                    <a href="${pageContext.request.contextPath}/customer-stats?action=viewReport" 
                       class="btn primary">Generate Report</a>
                </div>
                
                <div class="report-card">
                    <h3>Sales Performance Report</h3>
                    <p>Analyze sales trends and performance metrics</p>
                    <button class="btn" disabled>Coming Soon</button>
                </div>
                
                <div class="report-card">
                    <h3>Inventory Analysis</h3>
                    <p>Monitor stock levels and product performance</p>
                    <button class="btn" disabled>Coming Soon</button>
                </div>
                
                <div class="report-card">
                    <h3>Supplier Performance</h3>
                    <p>Evaluate supplier delivery and quality metrics</p>
                    <button class="btn" disabled>Coming Soon</button>
                </div>
            </div>
            
            <div class="quick-actions">
                <h3>Quick Actions</h3>
                <div class="action-buttons">
                    <a href="${pageContext.request.contextPath}/customer-stats?action=viewReport&startDate=2024-01-01&endDate=2024-03-31" 
                       class="btn small">Q1 2024 Report</a>
                    <a href="${pageContext.request.contextPath}/customer-stats?action=viewReport&startDate=2024-03-01&endDate=2024-03-31" 
                       class="btn small">March 2024</a>
                    <a href="${pageContext.request.contextPath}/customer-stats?action=viewReport&startDate=2024-02-01&endDate=2024-02-29" 
                       class="btn small">February 2024</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>