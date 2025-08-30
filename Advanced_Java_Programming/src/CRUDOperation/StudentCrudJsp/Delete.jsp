<%@ page import="java.sql.*,com.example.util.DBConnection" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("DELETE FROM students WHERE id=?");
    ps.setInt(1, id);
    ps.executeUpdate();

    response.sendRedirect("index.jsp");
%>