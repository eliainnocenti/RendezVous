package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.ORM.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

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
        if (user.getPaymentMethodType() != null) {
            System.out.println("Payment Method: " + user.getPaymentMethodType());
            System.out.println("Payment Data: \n" + user.getPaymentData());
        }

    }

    public void updateName(String newName) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.updateName(user.getUsername(), newName);
        user.setName(newName);
        System.out.println("Name updated successfully!");

    }

    public void updateSurname(String newSurname) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.updateSurname(user.getUsername(), newSurname);
        user.setSurname(newSurname);
        System.out.println("Surname updated successfully!");

    }

    public void updateAge(int newAge) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.updateAge(user.getUsername(), newAge);
        user.setAge(newAge);
        System.out.println("Age updated successfully!");

    }

    public ArrayList<String> getAllUsernames() throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        return userDAO.getAllUsernames();

    }

    public void updateUsername(String newUsername) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.updateUsername(user.getUsername(), newUsername);
        user.updateUsername(newUsername);
        System.out.println("Username updated successfully!");

    }

    public ArrayList<String> getAllEmails() throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        return userDAO.getAllEmails();

    }

    public void updateEmail(String newEmail) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.updateEmail(user.getUsername(), newEmail);
        user.updateEmail(newEmail);
        System.out.println("Email updated successfully!");

    }

    public void updatePassword(String newPassword) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.updatePassword(user.getUsername(), newPassword);
        user.updatePassword(newPassword);
        System.out.println("Password updated successfully!");

    }

    public void setPaymentMethod(String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) {

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("creditcard") || paymentMethod.equals("credit card")) {
            try {
                updateCreditCard(cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else if (paymentMethod.equals("PayPal")) {
            try {
                updatePayPal(cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Invalid payment method.");
        }


    }

    public void updatePaymentMethod() throws SQLException, ClassNotFoundException {

        // FIXME: obsolete method, to be removed (?)

    }

    public void updateCreditCard(String cardNumber, String cardExpirationDate, String cardSecurityCode) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        userDAO.updateCreditCard(user.getUsername(), cardNumber, cardExpirationDate, cardSecurityCode);
        user.setCreditCard(cardNumber, null, cardExpirationDate, cardSecurityCode);

        System.out.println("Credit Card updated successfully!");

    }

    public void updatePayPal(String accountEmail, String accountPassword) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

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