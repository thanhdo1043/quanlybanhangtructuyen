<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Server Error - Department Store</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
    <div class="container">
        <header>
            <h1>Online Department Store</h1>
        </header>

        <main>
            <div class="error-page">
                <h2>500 - Server Error</h2>
                <div class="error-icon">⚠️</div>
                <p>Something went wrong on our end. Please try again later.</p>
                <div class="error-actions">
                    <a href="${pageContext.request.contextPath}/" class="btn primary">Go Home</a>
                    <a href="javascript:location.reload()" class="btn">Retry</a>
                </div>
            </div>
        </main>
    </div>
</body>
</html>