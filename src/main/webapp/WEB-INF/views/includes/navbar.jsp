<%@ page import="com.ubik.formation.library2.dto.UserDto" %>
<%
    UserDto user = (UserDto) session.getAttribute("user");
%>

<div class="navbar">
    <div>
        <a href="<%= request.getContextPath() %>/books">Books List</a>
        <a href="<%= request.getContextPath() %>/authors">Authors List</a>
    </div>
    <div>
        <% if (user != null) { %>
        <a href="<%= request.getContextPath() %>/auth/logout"><%= user.getUsername() %> Log Out</a>
        <% } else { %>
        <a href="<%= request.getContextPath() %>/auth">Log In</a>
        <% } %>
    </div>
</div>
