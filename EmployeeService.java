package services;

import config.DBConnection;
import models.Employee;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class EmployeeService {

    public void addEmployee(Employee emp) {

        String sql = "INSERT INTO employees(emp_id,name,designation,base_salary) VALUES(?,?,?,?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, emp.getEmpId());
            stmt.setString(2, emp.getName());
            stmt.setString(3, emp.getDesignation());
            stmt.setDouble(4, emp.getBaseSalary());

            stmt.executeUpdate();

            System.out.println("Employee added successfully");

        } catch (Exception e) {

            System.out.println("Error adding employee");
            e.printStackTrace();
        }
    }


    public void viewEmployees() {

        String sql = "SELECT * FROM employees";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            System.out.println("\nEmployee List");

            while(rs.next()){

                System.out.println(
                        rs.getInt("emp_id") + " | " +
                        rs.getString("name") + " | " +
                        rs.getString("designation") + " | " +
                        rs.getDouble("base_salary")
                );
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}