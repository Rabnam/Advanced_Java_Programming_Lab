package OOP;
import java.util.*;
// Base class Employee
class Employee {
    private int id;
    private String name;
    protected double salary;

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }
    public void calculateSalary() {
        salary = 0;
    }

    public void displayEmployeeDetails() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: " + salary);
    }
}
class FullTimeEmployee extends Employee {
    private double basicSalary;
    private double bonus;

    public FullTimeEmployee(int id, String name, double basicSalary, double bonus) {
        super(id, name); // calling parent constructor
        this.basicSalary = basicSalary;
        this.bonus = bonus;
    }

    @Override
    public void calculateSalary() {
        salary = basicSalary + bonus;
    }
}
class PartTimeEmployee extends Employee {
    private double hoursWorked;
    private double hourlyRate;

    public PartTimeEmployee(int id, String name, double hoursWorked, double hourlyRate) {
        super(id, name);
        this.hoursWorked = hoursWorked;
        this.hourlyRate = hourlyRate;
    }

    @Override
    public void calculateSalary() {
        salary = hoursWorked * hourlyRate;
    }
}
public class PayrollSystem {
    public static void main(String[] args) {
        Employee emp1 = new FullTimeEmployee(1, "Rabnam", 35000, 10000);
        Employee emp2 = new PartTimeEmployee(2, "Aayush", 200, 150);

        emp1.calculateSalary();
        emp2.calculateSalary();
        emp1.displayEmployeeDetails();
        emp2.displayEmployeeDetails();
    }
}
