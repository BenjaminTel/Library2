<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="com.ubik.formation.library2.model.Author" %>

<%

    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>

<!DOCTYPE html>
<html>
<head>
    <title>Author List</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
<header>
    <h1>List of Authors</h1>
</header>
<main>

    <a href="<%= request.getContextPath() %>/authors/create">Add an author</a>
    <br/>

    <form action="<%= request.getContextPath() %>/authors/delete" method="post">
        <table>
            <thead>
            <tr>
                <th>Select</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Full Name</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                if (authors != null && !authors.isEmpty()) {
                    for (Author author : authors) {
            %>
            <tr>
                <td><input type="checkbox" name="authorIds" value="<%= author.getId() %>"/></td>
                <td><%= author.getFirstName() %>
                </td>
                <td><%= author.getLastName() %>
                </td>
                <td><%= author.getName() %>
                </td>
                <td><a href="<%= request.getContextPath() %>/authors/<%= author.getId()%>">Edit</a></td>
            </tr>
            <%
                }
            } else {
            %>
            <tr>
                <td colspan="4">No authors available</td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <br/>

        <button type="submit">Delete Selected Author(s)</button>
        <br/><br/>


        <%@ include file="/WEB-INF/views/includes/pagination.jsp" %>


    </form>
</main>

</body>
</html>
