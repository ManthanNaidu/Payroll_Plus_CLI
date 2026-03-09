package services;

import java.util.Scanner;

public class AuthService {

    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "1234";

    public boolean login(Scanner sc) {

        System.out.print("Username: ");
        String username = sc.next();

        System.out.print("Password: ");
        String password = sc.next();

        if(username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)){

            System.out.println("Login successful\n");
            return true;

        }else{

            System.out.println("Invalid credentials\n");
            return false;
        }
    }
}