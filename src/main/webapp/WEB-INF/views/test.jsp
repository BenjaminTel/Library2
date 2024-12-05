<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"
         
%>
<%-- import="com.ubik.formation.library2.model.Book"
         import="java.util.List" --%>
<!DOCTYPE html>
<html>
<head>
    <title>Books</title>
</head>

<body>
<h1>ceci est un test</h1>

<%-- <%
    List<Book> books = (List<Book>) request.getAttribute("books");
%> --%>
<main>
<%--     <% if (books != null) { %>
 --%><%--        <% if (status != null && "updated".equals(status)) { %>--%>
<%--            <div class="alert alert-success">--%>
<%--                Book updated with success.--%>
<%--            </div>--%>
<%--        <% } else if (status != null && "created".equals(status)) { %>--%>
<%--            <div class="alert alert-success">--%>
<%--                Book created with success.--%>
<%--            </div>--%>
<%--        <% } %>--%>
        <%-- <table class="table table-striped">
            <thead>
                <tr>

                </tr>
            </thead>
            <tbody>
                <% for (Book book: books) { %>
                    <tr>
                    </tr>
                <% } %>
                </tbody>
            </table> --%>

<%--            <div>--%>
<%--                <!-- Pagination -->--%>
<%--                <% if (Integer.valueOf(current_page) > 1) { %>--%>
<%--                <a href="<%= livreControllerUrl %>?action=list&page=<%= current_page - 1 %>">Previous</a>--%>
<%--                <% } %>--%>
<%--        --%>
<%--                <% for (int i = 1; i <= totalPages; i++) { %>--%>
<%--                <% if (current_page != i) { %>--%>
<%--                <a href="<%= livreControllerUrl %>?action=list&page=<%= i %>"><%= i %></a>--%>
<%--                <% } %>--%>
<%--                <% } %>--%>
<%--                <% if (current_page < totalPages) { %>--%>
<%--                <a href="<%= livreControllerUrl %>?action=list&page=<%= current_page +1 %>">Next</a>--%>
<%--                <% } %>--%>
<%--            </div>--%>
      <%--   <% } %> --%>
    </main>
</body>
</html>