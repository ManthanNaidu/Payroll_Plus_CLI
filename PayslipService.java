package services;

import config.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PayslipService {

    public void generatePayslip(int empId, int month, int year) {

        String sql = "SELECT * FROM payroll WHERE emp_id=? AND month=? AND year=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, empId);
            stmt.setInt(2, month);
            stmt.setInt(3, year);

            ResultSet rs = stmt.executeQuery();

            if(!rs.next()){
                System.out.println("Payslip not found.");
                return;
            }

            System.out.println("\n===== PAYSLIP =====");

            System.out.println("Employee ID : " + empId);
            System.out.println("Month       : " + month);
            System.out.println("Year        : " + year);
            System.out.println("--------------------------");

            System.out.println("Gross Salary: " + rs.getDouble("gross_salary"));
            System.out.println("Deductions  : " + rs.getDouble("deductions"));
            System.out.println("Net Salary  : " + rs.getDouble("net_salary"));

            System.out.println("==========================");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    public void viewPayrollReport(int month, int year) {

        String sql = "SELECT * FROM payroll WHERE month=? AND year=?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, month);
            stmt.setInt(2, year);

            ResultSet rs = stmt.executeQuery();

            System.out.println("\n===== Payroll Report =====");

            while(rs.next()){

                System.out.println(
                        rs.getInt("emp_id") + " | " +
                        rs.getDouble("gross_salary") + " | " +
                        rs.getDouble("deductions") + " | " +
                        rs.getDouble("net_salary")
                );
            }

        } catch(Exception e){
            e.printStackTrace();
        }
    }
}