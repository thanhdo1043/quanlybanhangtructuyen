<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Statistics</title>
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
            <h2>Customer Statistics by Revenue</h2>
            
            <form action="${pageContext.request.contextPath}/customer-stats" method="get" class="report-form">
                <input type="hidden" name="action" value="viewReport">
                <div class="form-group">
                    <label for="startDate">Start Date:</label>
                    <input type="date" id="startDate" name="startDate" value="${startDate}" required>
                </div>
                <div class="form-group">
                    <label for="endDate">End Date:</label>
                    <input type="date" id="endDate" name="endDate" value="${endDate}" required>
                </div>
                <button type="submit" class="btn primary">Generate Report</button>
            </form>

            <c:if test="${not empty customerStats}">
                <h3>Customer Revenue Report (${startDate} to ${endDate})</h3>
                
                <table class="data-table">
                    <thead>
                        <tr>
                            <th>Rank</th>
                            <th>Customer Name</th>
                            <th>Email</th>
                            <th>Total Orders</th>
                            <th>Total Revenue</th>
                            <th>Actions</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="stats" items="${customerStats}" varStatus="status">
                            <tr>
                                <td>${status.index + 1}</td>
                                <td>${stats.customer.fullName}</td>
                                <td>${stats.customer.email}</td>
                                <td>${stats.totalOrders}</td>
                                <td><fmt:formatNumber value="${stats.totalRevenue}" type="currency"/></td>
                                <td>
                                    <a href="${pageContext.request.contextPath}/customer-stats?action=customerDetails&customerId=${stats.customer.userId}&startDate=${startDate}&endDate=${endDate}" 
                                       class="btn small">View Details</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <c:if test="${empty customerStats && not empty startDate}">
                <p class="no-data">No data found for the selected criteria.</p>
            </c:if>
        </main>
    </div>
</body>
</html>