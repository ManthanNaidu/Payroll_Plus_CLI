package services;

import config.DBConnection;
import models.Attendance;

import java.sql.Connection;
import java.sql.PreparedStatement;

public class AttendanceService {

    public void recordAttendance(Attendance att) {

        try {

            Connection conn = DBConnection.getConnection();

            String sql = "INSERT INTO attendance(emp_id,month,year,working_days) VALUES(?,?,?,?)";

            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setInt(1, att.getEmpId());
            stmt.setInt(2, att.getMonth());
            stmt.setInt(3, att.getYear());
            stmt.setInt(4, att.getWorkingDays());

            stmt.executeUpdate();

            System.out.println("Attendance recorded successfully");

        } catch (Exception e) {

            e.printStackTrace();
        }
    }
}