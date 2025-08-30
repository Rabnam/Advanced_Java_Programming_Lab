<%@ page import="java.sql.*,com.example.util.DBConnection" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    String roll_no = request.getParameter("roll_no");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String program = request.getParameter("program");

    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement(
        "UPDATE students SET roll_no=?, name=?, email=?, program=? WHERE id=?"
    );
    ps.setString(1, roll_no);
    ps.setString(2, name);
    ps.setString(3, email);
    ps.setString(4, program);
    ps.setInt(5, id);
    ps.executeUpdate();

    response.sendRedirect("index.jsp");
%>

