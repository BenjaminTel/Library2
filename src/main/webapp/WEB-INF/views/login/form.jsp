<%@ page contentType="text/html; charset=UTF-8" %>
<%
    String error = (String) request.getAttribute("errorMessage");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>

<header>
    <h1>Login</h1>
</header>

<main>

    <%
        if (error != null) {
    %>
    <div>
        <p class="error error_message">
            <%= error %>
        </p>
    </div>
    <%
        }
    %>
    <form action="<%= request.getContextPath() %>/auth/login" method="post">
        <div>
            <label for="login">Username:</label>
            <input type="text" id="login" name="login" required>
        </div>
        <br/>
        <div>
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
        </div>
        <br/>
        <button type="submit">Login</button>
    </form>

</main>
</body>
</html>
