<%@ page import="java.sql.*,com.example.util.DBConnection" %>
<%
    String roll_no = request.getParameter("roll_no");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String program = request.getParameter("program");

    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement(
        "INSERT INTO students(roll_no, name, email, program) VALUES (?,?,?,?)"
    );
    ps.setString(1, roll_no);
    ps.setString(2, name);
    ps.setString(3, email);
    ps.setString(4, program);
    ps.executeUpdate();

    response.sendRedirect("index.jsp");
%>

