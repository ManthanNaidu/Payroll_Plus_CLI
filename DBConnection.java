package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBConnection {

    private static final String URL = "jdbc:sqlite:database/payroll.db";

    public static Connection getConnection() {

        Connection conn = null;

        try {

            conn = DriverManager.getConnection(URL);

            createTables(conn);

        } catch (Exception e) {

            System.out.println("Database connection error");
            e.printStackTrace();
        }

        return conn;
    }

    private static void createTables(Connection conn) {

        try {

            Statement stmt = conn.createStatement();

            String employeeTable =
                    "CREATE TABLE IF NOT EXISTS employees (" +
                    "emp_id INTEGER PRIMARY KEY," +
                    "name TEXT NOT NULL," +
                    "designation TEXT," +
                    "base_salary REAL)";

            String attendanceTable =
                    "CREATE TABLE IF NOT EXISTS attendance (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "emp_id INTEGER," +
                    "month INTEGER," +
                    "year INTEGER," +
                    "working_days INTEGER)";

            String payrollTable =
                    "CREATE TABLE IF NOT EXISTS payroll (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "emp_id INTEGER," +
                    "month INTEGER," +
                    "year INTEGER," +
                    "gross_salary REAL," +
                    "deductions REAL," +
                    "net_salary REAL)";

            stmt.execute(employeeTable);
            stmt.execute(attendanceTable);
            stmt.execute(payrollTable);

            System.out.println("Tables verified/created successfully.");

        } catch (Exception e) {

            System.out.println("Table creation failed.");
            e.printStackTrace();
        }
    }
}