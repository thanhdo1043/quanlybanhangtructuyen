<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Product Details</title>
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
            <c:if test="${not empty product}">
                <div class="product-details">
                    <div class="product-image">
                        <c:if test="${not empty product.imageUrl}">
                            <img src="${product.imageUrl}" alt="${product.name}">
                        </c:if>
                    </div>
                    <div class="product-info">
                        <h2>${product.name}</h2>
                        <p class="price">$${product.price}</p>
                        <p class="category">Category: ${product.category}</p>
                        <p class="stock">In stock: ${product.quantityInStock}</p>
                        
                        <c:if test="${not empty product.description}">
                            <div class="description">
                                <h3>Description</h3>
                                <p>${product.description}</p>
                            </div>
                        </c:if>

                        <c:if test="${not empty product.supplier}">
                            <div class="supplier">
                                <h3>Supplier</h3>
                                <p>${product.supplier.supplierName}</p>
                            </div>
                        </c:if>

                        <div class="actions">
                            <button class="btn primary">Add to Cart</button>
                            <a href="${pageContext.request.contextPath}/search" class="btn">Back to Search</a>
                        </div>
                    </div>
                </div>
            </c:if>

            <c:if test="${empty product}">
                <p class="error">Product not found.</p>
                <a href="${pageContext.request.contextPath}/search" class="btn">Back to Search</a>
            </c:if>
        </main>
    </div>
</body>
</html>