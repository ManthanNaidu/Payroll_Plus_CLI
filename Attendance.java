package models;

public class Attendance {

    private int empId;
    private int month;
    private int year;
    private int workingDays;

    public Attendance(int empId, int month, int year, int workingDays) {
        this.empId = empId;
        this.month = month;
        this.year = year;
        this.workingDays = workingDays;
    }

    public int getEmpId() {
        return empId;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getWorkingDays() {
        return workingDays;
    }
}