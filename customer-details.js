<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Customer Details</title>
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
            <c:if test="${not empty customer}">
                <div class="customer-header">
                    <h2>Customer Details: ${customer.fullName}</h2>
                    <div class="customer-info">
                        <div class="info-item">
                            <strong>Customer ID:</strong> ${customer.userId}
                        </div>
                        <div class="info-item">
                            <strong>Email:</strong> ${customer.email}
                        </div>
                        <div class="info-item">
                            <strong>Phone:</strong> ${not empty customer.phoneNumber ? customer.phoneNumber : 'N/A'}
                        </div>
                        <div class="info-item">
                            <strong>Address:</strong> ${not empty customer.address ? customer.address : 'N/A'}
                        </div>
                        <div class="info-item">
                            <strong>Registration Date:</strong> 
                            <fmt:formatDate value="${customer.registrationDate}" pattern="yyyy-MM-dd HH:mm"/>
                        </div>
                    </div>
                </div>

                <div class="report-period">
                    <h3>Transaction History 
                        <small>(<fmt:formatDate value="${startDate}" pattern="yyyy-MM-dd"/> 
                        to <fmt:formatDate value="${endDate}" pattern="yyyy-MM-dd"/>)</small>
                    </h3>
                </div>
                
                <c:choose>
                    <c:when test="${not empty transactions}">
                        <div class="transactions-summary">
                            <p><strong>Total Transactions:</strong> ${transactions.size()}</p>
                            <p><strong>Total Revenue:</strong> 
                                <fmt:formatNumber value="${transactions.stream().map(order -> order.totalAmount).reduce(0, (a, b) -> a.add(b))}" 
                                type="currency"/>
                            </p>
                        </div>
                        
                        <table class="data-table">
                            <thead>
                                <tr>
                                    <th>Order ID</th>
                                    <th>Order Date</th>
                                    <th>Total Amount</th>
                                    <th>Status</th>
                                    <th>Actions</th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="order" items="${transactions}">
                                    <tr>
                                        <td>#${order.orderId}</td>
                                        <td>
                                            <fmt:formatDate value="${order.orderDate}" pattern="yyyy-MM-dd HH:mm"/>
                                        </td>
                                        <td>
                                            <fmt:formatNumber value="${order.totalAmount}" type="currency"/>
                                        </td>
                                        <td>
                                            <span class="status status-${order.status.name().toLowerCase()}">
                                                ${order.status}
                                            </span>
                                        </td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/order-details?orderId=${order.orderId}" 
                                               class="btn small">View Order</a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <div class="no-data">
                            <p>No transactions found for this customer in the selected period.</p>
                        </div>
                    </c:otherwise>
                </c:choose>
                
                <div class="actions">
                    <a href="${pageContext.request.contextPath}/customer-stats?action=viewReport&startDate=${startDate}&endDate=${endDate}" 
                       class="btn">Back to Report</a>
                    <a href="${pageContext.request.contextPath}/customer-stats" 
                       class="btn primary">New Report</a>
                </div>
            </c:if>

            <c:if test="${empty customer}">
                <div class="error-message">
                    <h3>Customer Not Found</h3>
                    <p>The requested customer could not be found in the system.</p>
                    <a href="${pageContext.request.contextPath}/customer-stats" class="btn">Back to Reports</a>
                </div>
            </c:if>
        </main>
    </div>
</body>
</html>