package CRUDOperation.EmployeeAndDepartment;

import java.util.Scanner;

public class EmployeeMain {
    public static void main(String[] args) {
        EmployeeCRUDMenu crud = new EmployeeCRUDMenu();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Employee Management Menu =====");
            System.out.println("1. Insert Department");
            System.out.println("2. Insert Employee");
            System.out.println("3. Update Employee Salary");
            System.out.println("4. Delete Employee");
            System.out.println("5. View All Employees");
            System.out.println("6. Fetch Employees by Department");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Department ID: ");
                    String deptId = sc.nextLine();
                    System.out.print("Enter Department Name: ");
                    String deptName = sc.nextLine();
                    crud.insertDepartment(deptId, deptName);
                    break;

                case 2:
                    System.out.print("Enter Employee ID: ");
                    String empId = sc.nextLine();
                    System.out.print("Enter Employee Name: ");
                    String empName = sc.nextLine();
                    System.out.print("Enter Department ID: ");
                    String deptForEmp = sc.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = sc.nextDouble();
                    crud.insertEmployee(empId, empName, deptForEmp, salary);
                    break;

                case 3:
                    System.out.print("Enter Employee ID: ");
                    String empIdUpdate = sc.nextLine();
                    System.out.print("Enter New Salary: ");
                    double newSalary = sc.nextDouble();
                    crud.updateEmployeeSalary(empIdUpdate, newSalary);
                    break;

                case 4:
                    System.out.print("Enter Employee ID to delete: ");
                    String empIdDelete = sc.nextLine();
                    crud.deleteEmployee(empIdDelete);
                    break;

                case 5:
                    crud.viewAllEmployees();
                    break;

                case 6:
                    System.out.print("Enter Department Name: ");
                    String deptSearch = sc.nextLine();
                    crud.fetchEmployeesByDepartment(deptSearch);
                    break;

                case 7:
                    System.out.println("Exiting program...");
                    sc.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}
