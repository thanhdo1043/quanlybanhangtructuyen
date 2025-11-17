<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Online Department Store</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Online Department Store Management System</h1>
        </header>

        <main>
            <div class="welcome-section">
                <h2>Welcome to Our Department Store</h2>
                <p>Please select your role to continue:</p>
                
                <div class="role-selection">
                    <div class="role-card">
                        <h3>Customer</h3>
                        <p>Search for products and place orders</p>
                        <a href="${pageContext.request.contextPath}/search" class="btn">Enter as Customer</a>
                    </div>
                    
                    <div class="role-card">
                        <h3>Management Staff</h3>
                        <p>View statistics and manage inventory</p>
                        <a href="${pageContext.request.contextPath}/customer-stats" class="btn primary">Enter as Manager</a>
                    </div>
                    
                    <div class="role-card">
                        <h3>Sale Staff</h3>
                        <p>Process orders and manage deliveries</p>
                        <a href="${pageContext.request.contextPath}/orders" class="btn">Enter as Sale Staff</a>
                    </div>
                </div>
            </div>
        </main>
    </div>
</body>
</html>