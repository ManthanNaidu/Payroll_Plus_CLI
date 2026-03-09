package models;

public class Employee {

    private int empId;
    private String name;
    private String designation;
    private double baseSalary;

    public Employee(int empId, String name, String designation, double baseSalary) {
        this.empId = empId;
        this.name = name;
        this.designation = designation;
        this.baseSalary = baseSalary;
    }

    public int getEmpId() {
        return empId;
    }

    public String getName() {
        return name;
    }

    public String getDesignation() {
        return designation;
    }

    public double getBaseSalary() {
        return baseSalary;
    }
}