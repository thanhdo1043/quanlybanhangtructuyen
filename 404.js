<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Page Not Found - Department Store</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Online Department Store</h1>
        </header>

        <main>
            <div class="error-page">
                <h2>404 - Page Not Found</h2>
                <div class="error-icon">🔍</div>
                <p>The page you are looking for does not exist.</p>
                <div class="error-actions">
                    <a href="${pageContext.request.contextPath}/" class="btn primary">Go Home</a>
                    <a href="javascript:history.back()" class="btn">Go Back</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>