<%
    int currentPage = (Integer) request.getAttribute("currentPage");
    int size = (Integer) request.getAttribute("size");
    int totalPages = (Integer) request.getAttribute("totalPages");
    String sort = (String) request.getAttribute("sort");
    String direction = (String) request.getAttribute("direction");

    String sortBy = "";
    if (sort != null && direction != null) {
        sortBy += "&sort=" + sort + "&direction=" + direction;
    }
%>
<div>
    <div>
        <% if (currentPage > 0) { %>
        <a href="?page=<%= currentPage - 1 %>&size=<%= size %><%= sortBy %>">Prev</a>
        <% } %>
        <%= currentPage + 1 %>/<%= totalPages %>
        <% if (currentPage + 1 < totalPages) { %>
        <a href="?page=<%= currentPage + 1 %>&size=<%= size %><%= sortBy %>">Next</a>
        <% } %>
    </div>
</div>