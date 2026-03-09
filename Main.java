import models.Employee;
import models.Attendance;

import services.*;

import utils.InputValidator;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        AuthService authService = new AuthService();
        EmployeeService employeeService = new EmployeeService();
        AttendanceService attendanceService = new AttendanceService();
        SalaryService salaryService = new SalaryService();
        PayslipService payslipService = new PayslipService();

        while(true){

            System.out.println("\n==== PayrollPlus CLI ====");
            System.out.println("1 Admin Login");
            System.out.println("2 Exit");

            int option = sc.nextInt();

            switch(option){

                case 1:

                    if(authService.login(sc)){

                        boolean loggedIn = true;

                        while(loggedIn){

                            System.out.println("\n==== Admin Menu ====");
                            System.out.println("1 Add Employee");
                            System.out.println("2 View Employees");
                            System.out.println("3 Record Attendance");
                            System.out.println("4 Calculate Salary");
                            System.out.println("5 Generate Payslip");
                            System.out.println("6 View Payroll Report");
                            System.out.println("7 Logout");

                            int choice = sc.nextInt();

                            switch(choice){

                                case 1:

                                    System.out.print("Employee ID: ");
                                    int id = sc.nextInt();
                                    sc.nextLine();

                                    System.out.print("Name: ");
                                    String name = sc.nextLine();

                                    System.out.print("Designation: ");
                                    String designation = sc.nextLine();

                                    System.out.print("Base Salary: ");
                                    double salary = sc.nextDouble();

                                    if(InputValidator.isValidSalary(salary)){

                                        Employee emp = new Employee(id,name,designation,salary);
                                        employeeService.addEmployee(emp);
                                    }

                                    break;


                                case 2:

                                    employeeService.viewEmployees();
                                    break;


                                case 3:

                                    System.out.print("Employee ID: ");
                                    int empId = sc.nextInt();

                                    System.out.print("Month: ");
                                    int month = sc.nextInt();

                                    System.out.print("Year: ");
                                    int year = sc.nextInt();

                                    System.out.print("Working Days: ");
                                    int days = sc.nextInt();

                                    Attendance att = new Attendance(empId,month,year,days);
                                    attendanceService.recordAttendance(att);

                                    break;


                                case 4:

                                    System.out.print("Employee ID: ");
                                    int sEmpId = sc.nextInt();

                                    System.out.print("Month: ");
                                    int sMonth = sc.nextInt();

                                    System.out.print("Year: ");
                                    int sYear = sc.nextInt();

                                    salaryService.calculateSalary(sEmpId,sMonth,sYear);

                                    break;


                                case 5:

                                    System.out.print("Employee ID: ");
                                    int pEmpId = sc.nextInt();

                                    System.out.print("Month: ");
                                    int pMonth = sc.nextInt();

                                    System.out.print("Year: ");
                                    int pYear = sc.nextInt();

                                    payslipService.generatePayslip(pEmpId,pMonth,pYear);

                                    break;


                                case 6:

                                    System.out.print("Month: ");
                                    int rMonth = sc.nextInt();

                                    System.out.print("Year: ");
                                    int rYear = sc.nextInt();

                                    payslipService.viewPayrollReport(rMonth,rYear);

                                    break;


                                case 7:

                                    loggedIn = false;
                                    System.out.println("Logged out");
                                    break;


                                default:

                                    System.out.println("Invalid option");
                            }
                        }
                    }

                    break;


                case 2:

                    System.out.println("Exiting system");
                    System.exit(0);


                default:

                    System.out.println("Invalid option");
            }
        }
    }
}