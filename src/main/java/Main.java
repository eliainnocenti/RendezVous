package main.java;

import main.java.BusinessLogic.*;
import main.java.DomainModel.*;
import main.java.ORM.*;

import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        handleLoginAction();

    }

    public static void handleLoginAction() throws SQLException, ClassNotFoundException {

        String input;

        Scanner scanner = new Scanner(System.in);
        LoginController loginController = new LoginController();

        do {
            System.out.println(
                    """
                     RENDEZVOUS
                     1. Sign in
                     2. Sign up
                     3. Admin
                     4. Exit
                    """
            );

            input = scanner.nextLine();

            if (input.equals("1")) {
                User user = loginController.login();
                if (user != null)
                    handleUserAction(user);
            } else if (input.equals("2")) {
                loginController.register();
            } else if (input.equals("3")) {
                if (loginController.adminLogin())
                    handleAdminAction();
            } else if (input.equals("4")) {
                break;
            } else {
                System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleUserAction(User user) {
        // TODO: implement this method

        Scanner scanner = new Scanner(System.in);

        // example
        System.out.println("" +
                "1. View profile\n" +
                "2. Edit profile\n" +
                "3. View events\n" +
                "4. View event page\n" +
                "5. Event management\n" +
                "6. User profile\n" +
                "7. Log out\n");

        String input = scanner.nextLine();

    }

    public static void handleEventPageAction() {
        // TODO: implement this method

    }

    public static void handleEventManagementAction() {
        // TODO: implement this method

    }

    public static void handleUserProfileAction() {
        // TODO: implement this method

    }

    public static void handleAdminAction() {
        // TODO: implement this method

        Scanner scanner = new Scanner(System.in);

        // example
        System.out.println("" +
                "1. View users\n" +
                "2. View user\n" +
                "3. Remove user\n" +
                "4. View events\n" +
                "5. View event\n" +
                "6. Remove event\n" +
                "7. Log out\n");

        String input = scanner.nextLine();

    }

}