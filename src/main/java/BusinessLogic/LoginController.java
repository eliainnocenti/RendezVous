package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.ORM.UserDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class LoginController {

    public LoginController() {}

    // sign up
    public void register() throws SQLException, ClassNotFoundException { // TODO: check payment method

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        // personal data
        System.out.println("Welcome! Please provide the following information to register:");
        System.out.println("Name: ");
        String name = scanner.nextLine();
        System.out.println("Surname: ");
        String surname = scanner.nextLine();
        System.out.println("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        // login data
        String username, email, password;
        do {
            System.out.println("Username, Email and Password are required fields.");
            System.out.println("Username: ");
            username = scanner.nextLine();
            System.out.println("Email: ");
            email = scanner.nextLine();
            System.out.println("Password: ");
            password = scanner.nextLine();
        } while (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty());

        // payment data
        System.out.println("Payment Method: ");
        String paymentMethod = scanner.nextLine();
        if (paymentMethod.equals("CreditCard")) {
            System.out.println("Card Number: ");
            String cardNumber = scanner.nextLine();
            System.out.println("Card Expiration Date: ");
            String cardExpirationDate = scanner.nextLine();
            System.out.println("Card Security Code: ");
            String cardSecurityCode = scanner.nextLine();
            userDAO.addUser(name, surname, age, username, email, password, paymentMethod, cardNumber, cardExpirationDate, cardSecurityCode);
        } else if (paymentMethod.equals("PayPal")) {
            //System.out.println("Unique Code: ");
            //String uniqueCode = scanner.nextLine();
            System.out.println("\tEmail: ");
            String accountEmail = scanner.nextLine();
            System.out.println("\tPassword: ");
            String accountPassword = scanner.nextLine();
            userDAO.addUser(name, surname, age, username, email, password, paymentMethod, null, accountEmail, accountPassword);
        } else {
            userDAO.addUser(name, surname, age, username, email, password);
        }

    }

    // sign in
    public User login() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("Username: ");
        String username = scanner.nextLine();
        System.out.println("Password: ");
        String password = scanner.nextLine();

        return userDAO.checkPassword(username, password);

    }

    // admin login
    public boolean adminLogin() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("Password Admin: ");
        String password = scanner.nextLine();

        return password.equals("admin");

    }

}