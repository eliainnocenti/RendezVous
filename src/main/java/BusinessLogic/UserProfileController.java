package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.ORM.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserProfileController {

    User user;

    public UserProfileController(User user) { this.user = user; }

    public void viewProfile() {

        System.out.println("\nName: " + user.getName());
        System.out.println("Surname: " + user.getSurname());
        System.out.println("Age: " + user.getAge());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        if (user.getPaymentMethod() != null) {
            System.out.println("Payment Method: " + user.getPaymentMethod());
            System.out.println("Payment Data: \n" + user.getPaymentData());
        }

    }

    public void updateName() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("\nNew Name: ");
        String newName = scanner.nextLine();

        userDAO.updateName(user.getUsername(), newName);
        user.setName(newName);
        System.out.println("Name updated successfully!");

    }

    public void updateSurname() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("\nNew Surname: ");
        String newSurname = scanner.nextLine();

        userDAO.updateSurname(user.getUsername(), newSurname);
        user.setSurname(newSurname);
        System.out.println("Surname updated successfully!");

    }

    public void updateAge() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("\nNew Age: ");
        int newAge = scanner.nextInt();

        userDAO.updateAge(user.getUsername(), newAge);
        user.setAge(newAge);
        System.out.println("Age updated successfully!");

    }

    public void updateUsername() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        ArrayList<String> usernames = userDAO.getAllUsernames();

        String newUsername;

        do {

            System.out.println("\nNew Username: ");
            newUsername = scanner.nextLine();

            if (newUsername == null || newUsername.isEmpty()) {
                System.out.println("Username cannot be null or empty.");
                continue;
            }

            if (usernames.contains(newUsername)) {
                System.out.println("Username already exists.");
            }

        } while (usernames.contains(newUsername));

        userDAO.updateUsername(user.getUsername(), newUsername);
        user.updateUsername(newUsername);
        System.out.println("Username updated successfully!");

    }

    public void updateEmail() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        ArrayList<String> emails = userDAO.getAllEmails();

        String newEmail;

        System.out.println();

        do {

            System.out.println("New Email: ");
            newEmail = scanner.nextLine();

            if (newEmail == null || newEmail.isEmpty()) {
                System.out.println("Email cannot be null or empty.");
                continue;
            }

            if (emails.contains(newEmail)) {
                System.out.println("Email already exists.");
            }

        } while (emails.contains(newEmail));

        userDAO.updateEmail(user.getUsername(), newEmail);
        user.updateEmail(newEmail);
        System.out.println("Email updated successfully!");

    }

    public void updatePassword() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        String newPassword;

        System.out.println();

        do {

            System.out.println("New Password: ");
            newPassword = scanner.nextLine();

            if (newPassword == null || newPassword.isEmpty()) {
                System.out.println("Password cannot be null or empty.");
            }

        } while (newPassword == null || newPassword.isEmpty());

        userDAO.updatePassword(user.getUsername(), newPassword);
        user.updatePassword(newPassword);
        System.out.println("Password updated successfully!");

    }

    public void setPaymentMethod() {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nPayment Method: ");
        String paymentMethod = scanner.nextLine();

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("creditcard") || paymentMethod.equals("credit card")) {
            try {
                updateCreditCard();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (paymentMethod.equals("PayPal")) {
            try {
                updatePayPal();
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid payment method.");
        }


    }

    public void updatePaymentMethod() throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.removePaymentMethod(user.getUsername());

        setPaymentMethod();

    }

    public void updateCreditCard() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        userDAO.removePaymentMethod(user.getUsername());

        System.out.println();
        System.out.println("Card Number: ");
        String cardNumber = scanner.nextLine();
        System.out.println("Card Expiration Date: ");
        String cardExpirationDate = scanner.nextLine();
        System.out.println("Card Security Code: ");
        String cardSecurityCode = scanner.nextLine();

        userDAO.updateCreditCard(user.getUsername(), cardNumber, cardExpirationDate, cardSecurityCode);
        user.setCreditCard(cardNumber, null, cardExpirationDate, cardSecurityCode);

        System.out.println("Credit Card updated successfully!");

    }

    public void updatePayPal() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        userDAO.removePaymentMethod(user.getUsername());

        System.out.println();
        System.out.println("Email: ");
        String accountEmail = scanner.nextLine();
        System.out.println("Password: ");
        String accountPassword = scanner.nextLine();

        userDAO.updatePayPal(user.getUsername(), accountEmail, accountPassword);

        String uniqueCode = userDAO.getUser(user.getUsername()).getPaymentCode()[1];
        user.setPayPal(uniqueCode, accountEmail, accountPassword);

        System.out.println("PayPal account updated successfully!");

    }

    public void removePaymentMethod() throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.removePaymentMethod(user.getUsername());
        user.setPaymentMethod(null);

        System.out.println("\nPayment method removed successfully!");

    }

}