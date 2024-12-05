<%@ page import="com.ubik.formation.library2.model.Author" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    Author author = (Author) request.getAttribute("author");
    String action = author != null && author.getId() != null ? "update" : "add";
%>
<!DOCTYPE html>
<html>
<head>
    <title><%= action.equals("update") ? "Edit Author" : "Add New Author" %></title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
<header>
    <h1><%= action.equals("update") ? "Edit an Author" : "Add a new Author" %></h1>
</header>
    <main>
        <form action="<%= request.getContextPath() %>/authors/save" method="post">
            <input type="hidden" name="id" value="<%= action.equals("update") ? author.getId():  ""%>"/>
            <label for="firstName">First name :</label>
            <input type="text" id="firstName" name="firstName" required value="<%= action.equals("update") ? author.getFirstName() : "" %>"/>
            <br/><br/>

            <label for="lastName">Last name :</label>
            <input type="text" id="lastName" name="lastName" required value="<%= action.equals("update") ? author.getLastName() : "" %>"/>
            <br/><br/>

            <button type="submit">Submit</button>
        </form>

        <br/>
        <a href="<%= request.getContextPath() %>/authors">Back to Authors</a>
    </main>
</body>
</html>
