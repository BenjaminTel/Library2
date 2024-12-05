<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Welcome Page</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
    <%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
    <header>
        <h1>Welcome to the Library</h1>
    </header>
    <main>
        <p>
	        This is the project library with the second stack.
	        It's about using Spring MVC, Hibernate, Liquibase, SLF4J etc.
        </p>
    </main>
</body>
</html>
