package CRUDOperation.EmployeeAndDepartment;

import CRUDOperation.DBConnection;
import java.sql.*;

public class EmployeeCRUDMenu {
    private Connection conn;

    // Constructor
    public EmployeeCRUDMenu() {
        conn = DBConnection.getConnection(); // getConnection() should handle exceptions internally
        if (conn != null) {
            System.out.println("Connected to database successfully.");
        } else {
            System.out.println("Database connection failed! CRUD operations will not work.");
        }
    }

    // Insert Department
    public void insertDepartment(String deptId, String deptName) {
        if (conn == null) return; // avoid NullPointerException
        String sql = "INSERT INTO Department (dept_id, dept_name) VALUES (?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, deptId);
            ps.setString(2, deptName);
            ps.executeUpdate();
            System.out.println("Department inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting department: " + e.getMessage());
        }
    }

    // Insert Employee
    public void insertEmployee(String empId, String empName, String deptId, double salary) {
        if (conn == null) return;
        String sql = "INSERT INTO Employee (emp_id, emp_name, dept_id, salary) VALUES (?, ?, ?, ?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, empId);
            ps.setString(2, empName);
            ps.setString(3, deptId);
            ps.setDouble(4, salary);
            ps.executeUpdate();
            System.out.println("Employee inserted successfully.");
        } catch (SQLException e) {
            System.out.println("Error inserting employee: " + e.getMessage());
        }
    }

    // Update Employee Salary
    public void updateEmployeeSalary(String empId, double newSalary) {
        if (conn == null) return;
        String sql = "UPDATE Employee SET salary = ? WHERE emp_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setDouble(1, newSalary);
            ps.setString(2, empId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Salary updated successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error updating salary: " + e.getMessage());
        }
    }

    // Delete Employee
    public void deleteEmployee(String empId) {
        if (conn == null) return;
        String sql = "DELETE FROM Employee WHERE emp_id = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, empId);
            int rows = ps.executeUpdate();
            if (rows > 0) {
                System.out.println("Employee deleted successfully.");
            } else {
                System.out.println("Employee not found.");
            }
        } catch (SQLException e) {
            System.out.println("Error deleting employee: " + e.getMessage());
        }
    }

    // View All Employees with Department
    public void viewAllEmployees() {
        if (conn == null) return;
        String sql = "SELECT e.emp_id, e.emp_name, e.salary, d.dept_name " +
                "FROM Employee e JOIN Department d ON e.dept_id = d.dept_id";
        try (PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("\nEmpID | EmpName | Salary | Department");
            System.out.println("-------------------------------------");
            while (rs.next()) {
                System.out.printf("%s | %s | %.2f | %s%n",
                        rs.getString("emp_id"),
                        rs.getString("emp_name"),
                        rs.getDouble("salary"),
                        rs.getString("dept_name"));
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees: " + e.getMessage());
        }
    }

    // Fetch Employees by Department
    public void fetchEmployeesByDepartment(String deptName) {
        if (conn == null) return;
        String sql = "SELECT e.emp_id, e.emp_name, e.salary " +
                "FROM Employee e JOIN Department d ON e.dept_id = d.dept_id " +
                "WHERE d.dept_name = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, deptName);
            try (ResultSet rs = ps.executeQuery()) {
                System.out.println("\nEmpID | EmpName | Salary");
                System.out.println("------------------------");
                while (rs.next()) {
                    System.out.printf("%s | %s | %.2f%n",
                            rs.getString("emp_id"),
                            rs.getString("emp_name"),
                            rs.getDouble("salary"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error fetching employees by department: " + e.getMessage());
        }
    }
}
