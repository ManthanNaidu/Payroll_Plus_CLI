package services;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class SalaryService {

    public void calculateSalary(int empId, int month, int year) {

        try {

            Connection conn = DBConnection.getConnection();

            // get employee salary
            String empQuery = "SELECT base_salary FROM employees WHERE emp_id=?";
            PreparedStatement empStmt = conn.prepareStatement(empQuery);
            empStmt.setInt(1, empId);

            ResultSet empRs = empStmt.executeQuery();

            if(!empRs.next()){
                System.out.println("Employee not found.");
                return;
            }

            double baseSalary = empRs.getDouble("base_salary");

            // get attendance
            String attQuery = "SELECT working_days FROM attendance WHERE emp_id=? AND month=? AND year=?";
            PreparedStatement attStmt = conn.prepareStatement(attQuery);

            attStmt.setInt(1, empId);
            attStmt.setInt(2, month);
            attStmt.setInt(3, year);

            ResultSet attRs = attStmt.executeQuery();

            if(!attRs.next()){
                System.out.println("Attendance not found. Salary cannot be calculated.");
                return;
            }

            int workingDays = attRs.getInt("working_days");

            int totalWorkingDays = 30;

            double grossSalary = (baseSalary / totalWorkingDays) * workingDays;

            double deductions = grossSalary * 0.10;

            double netSalary = grossSalary - deductions;

            // insert payroll record
            String insertPayroll = "INSERT INTO payroll(emp_id,month,year,gross_salary,deductions,net_salary) VALUES(?,?,?,?,?,?)";

            PreparedStatement payStmt = conn.prepareStatement(insertPayroll);

            payStmt.setInt(1, empId);
            payStmt.setInt(2, month);
            payStmt.setInt(3, year);
            payStmt.setDouble(4, grossSalary);
            payStmt.setDouble(5, deductions);
            payStmt.setDouble(6, netSalary);

            payStmt.executeUpdate();

            System.out.println("Salary calculated successfully.");
            System.out.println("Gross Salary: " + grossSalary);
            System.out.println("Deductions: " + deductions);
            System.out.println("Net Salary: " + netSalary);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}