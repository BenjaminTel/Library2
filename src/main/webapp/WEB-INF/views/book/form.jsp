<%@ page
        language="java"
        contentType="text/html; charset=UTF-8"
        pageEncoding="UTF-8"
        import="java.util.List"
        import="com.ubik.formation.library2.model.Author"
%>
<%@ page import="com.ubik.formation.library2.dto.BookDto" %>

<%
    BookDto bookDto = (BookDto) request.getAttribute("book");
    String action = bookDto != null && bookDto.getId() != null ? "update" : "save";

    String tags = "";
    String separator = ",";
    List<String> tagList = bookDto != null ? bookDto.getTagNames() : null;
    if (tagList != null) {
        for (String tagName : tagList) {
            tags += tagName + separator;
        }
    }
    List<Author> authors = (List<Author>) request.getAttribute("authors");

%>
<!DOCTYPE html>
<html lang="en">
<head>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/css/styles.css">
    <title><%= action.equals("update") ? "Edit Book" : "Add New Book" %>
    </title>
</head>
<body>
<%@ include file="/WEB-INF/views/includes/navbar.jsp" %>
<header>
    <h1><%= action.equals("update") ? "Edit Book" : "Add New Book" %>
    </h1>
</header>
<form action="<%= request.getContextPath() %>/books/save" method="post">

    <input type="hidden" name="id" value="<%= action.equals("update") ? bookDto.getId() : ""%>"/>

    <label for="title">Title:</label>
    <input type="text" id="title" name="title" value="<%= action.equals("update") ? bookDto.getTitle() : "" %>"
           required>
    <br/><br/>

    <label for="publicationDate">Publication Date:</label>
    <input type="date" id="publicationDate" name="publicationDate"
           value="<%= action.equals("update") ? bookDto.getPublicationDate() : "" %>" required>
    <br/><br/>

    <label for="author">Author:</label>
    <select id="author" name="authorId" required>
        <%
            if (authors != null) {
                for (Author author : authors) {
        %>
        <option value="<%= author.getId() %>" <%= bookDto != null && bookDto.getAuthorId() != null && bookDto.getAuthorId().equals(author.getId()) ? "selected" : "" %>>
            <%= author.getName() %>
        </option>
        <%
                }
            }
        %>
    </select>
    <br/><br/>

    <label for="tagNames">Tags (separate values by a comma):</label>
    <input type="text" id="tagNames" name="tagNames" value="<%= tags %>"/>
    <br/><br/>

    <button type="submit"><%= action.equals("update") ? "Update Book" : "Save Book" %>
    </button>
</form>

<br/>
<a href="<%= request.getContextPath() %>/books">Back to Books</a>

</body>
</html>
