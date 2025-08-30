<%@ page import="java.sql.*,com.example.util.DBConnection" %>
<%
    int id = Integer.parseInt(request.getParameter("id"));
    Connection con = DBConnection.getConnection();
    PreparedStatement ps = con.prepareStatement("SELECT * FROM students WHERE id=?");
    ps.setInt(1, id);
    ResultSet rs = ps.executeQuery();
    rs.next();
%>
<!DOCTYPE html>
<html>
<head><title>Edit Student</title></head>
<body>
<h2>Edit Student</h2>
<form action="update.jsp" method="post">
    <input type="hidden" name="id" value="<%= rs.getInt("id") %>">
    Roll No: <input type="text" name="roll_no" value="<%= rs.getString("roll_no") %>"><br>
    Name: <input type="text" name="name" value="<%= rs.getString("name") %>"><br>
    Email: <input type="email" name="email" value="<%= rs.getString("email") %>"><br>
    Program: <input type="text" name="program" value="<%= rs.getString("program") %>"><br>
    <input type="submit" value="Update">
</form>
</body>
</html>

