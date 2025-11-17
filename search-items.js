<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Search Items</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Online Department Store</h1>
            <nav>
                <a href="${pageContext.request.contextPath}/">Home</a>
                <a href="${pageContext.request.contextPath}/search">Search Items</a>
            </nav>
        </header>

        <main>
            <h2>Search Products</h2>
            
            <form action="${pageContext.request.contextPath}/search" method="get" class="search-form">
                <input type="hidden" name="action" value="search">
                <input type="text" name="keyword" value="${param.keyword}" 
                       placeholder="Enter product name..." required>
                <button type="submit">Search</button>
            </form>

            <c:if test="${not empty searchKeyword}">
                <h3>Search Results for: "${searchKeyword}"</h3>
            </c:if>

            <c:choose>
                <c:when test="${not empty products}">
                    <div class="product-grid">
                        <c:forEach var="product" items="${products}">
                            <div class="product-card">
                                <c:if test="${not empty product.imageUrl}">
                                    <img src="${product.imageUrl}" alt="${product.name}">
                                </c:if>
                                <h4>${product.name}</h4>
                                <p class="price">$${product.price}</p>
                                <p class="stock">In stock: ${product.quantityInStock}</p>
                                <a href="${pageContext.request.contextPath}/search?action=details&productId=${product.productId}" 
                                   class="btn">View Details</a>
                            </div>
                        </c:forEach>
                    </div>
                </c:when>
                <c:when test="${not empty searchKeyword}">
                    <p class="no-results">No products found matching your search criteria.</p>
                </c:when>
            </c:choose>
        </main>
    </div>
</body>
</html>