package utils;

public class InputValidator {

    public static boolean isValidSalary(double salary) {

        if(salary <= 0){
            System.out.println("Salary must be greater than 0");
            return false;
        }

        return true;
    }

}