<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
        import="com.ubik.formation.library2.model.Book"
        import="com.ubik.formation.library2.model.Tag"
        import="java.util.List"
%>
<%
    List<Book> books = (List<Book>) request.getAttribute("books");
    int currentPage = (Integer) request.getAttribute("currentPage");
    int size = (Integer) request.getAttribute("size");
    String sort = (String) request.getAttribute("sort");
    String direction = (String) request.getAttribute("direction");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Books List</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
</head>
<body>
<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
<header>
    <h1>List of books</h1>
</header>

<main>

    <a href="<%= request.getContextPath() %>/books/create">Add a book</a>

    <%
        if (books != null && !books.isEmpty()) {
    %>
    <form action="<%= request.getContextPath() %>/books/delete" method="post">

        <table>
            <thead>
            <tr>
                <th>Selection</th>
                <th>Title</th>
                <th>Publication date
                    <a href="<%= request.getContextPath() %>/books?page=<%= currentPage %>&size=<%= size %>&sort<%= sort %>&direction=ASC">
                        &#9650;
                    </a>
                    <a href="<%= request.getContextPath() %>/books?page=<%= currentPage %>&size=<%= size %>&sort<%= sort %>&direction=DESC">
                        &#9660;
                    </a></th>

                <th>Author</th>
                <th>Tags</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
            <%
                for (Book book : books) {
            %>
            <tr>
                <td><input type="checkbox" name="bookIds" value="<%= book.getId() %>"/></td>
                <td><%= book.getTitle() %>
                </td>
                <td><%= book.getPublicationDate() %>
                </td>
                <td><%= (book.getAuthor() != null) ? book.getAuthor().getName() : "Inconnu" %>
                </td>
                <td>
                    <%
                        if (book.getTags() != null && !book.getTags().isEmpty()) {
                            for (Tag tag : book.getTags()) {
                    %>
                    <%= tag.getName() %>
                    <% }
                    }
                    %>
                </td>
                <td><a href="<%= request.getContextPath() %>/books/<%= book.getId()%>">Edit</a></td>
            </tr>
            <%
                }
            %>
            </tbody>
        </table>
        <br/>

        <button type="submit">Delete Selected Book(s)</button>
        <br/><br/>
    </form>
    <%
    } else {
    %>
    <p>No book available.</p>
    <%
        }
    %>

    <jsp:include page="//WEB-INF/views/includes/pagination.jsp" />
</main>

<footer>
</footer>
</body>
</html>
