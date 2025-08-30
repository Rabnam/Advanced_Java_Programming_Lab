<%@ page import="java.sql.*,com.example.util.DBConnection" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Management</title>
</head>
<body>
<h2>Student List</h2>

<table border="1" cellpadding="5">
    <tr>
        <th>ID</th>
        <th>Roll No</th>
        <th>Name</th>
        <th>Email</th>
        <th>Program</th>
        <th>Actions</th>
    </tr>
    <%
        Connection con = DBConnection.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM students");
        while(rs.next()) {
    %>
    <tr>
        <td><%= rs.getInt("id") %></td>
        <td><%= rs.getString("roll_no") %></td>
        <td><%= rs.getString("name") %></td>
        <td><%= rs.getString("email") %></td>
        <td><%= rs.getString("program") %></td>
        <td>
            <a href="edit.jsp?id=<%= rs.getInt("id") %>">Edit</a> |
            <a href="delete.jsp?id=<%= rs.getInt("id") %>">Delete</a>
        </td>
    </tr>
    <% } %>
</table>

<hr>
<h2>Add Student</h2>
<form action="insert.jsp" method="post">
    Roll No: <input type="text" name="roll_no" required><br>
    Name: <input type="text" name="name" required><br>
    Email: <input type="email" name="email" required><br>
    Program: <input type="text" name="program" required><br>
    <input type="submit" value="Add Student">
</form>

</body>
</html>

