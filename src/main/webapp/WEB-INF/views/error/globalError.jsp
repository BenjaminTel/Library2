<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Error Page</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
<header>
    <h1 class="error">An error occurred</h1>
</header>
<main>
    <div>
        <p class="error error_message">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "An unknown error occurred." %>
        </p>
    </div>
</main>
</body>
</html>
