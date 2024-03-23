package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.ORM.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

public class AdminUserController {

    public void viewUsers() throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        ArrayList<User> allUsers = userDAO.getAllUsers();

        System.out.println("\n+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");
        System.out.println("| ID | Name       | Surname    | Age | Username         | Email                            | Password           | CreditCard   | PayPal |");
        System.out.println("+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");
        for (User user : allUsers) {
            System.out.printf("| %-3s| %-11s| %-11s| %-4s| %-17s| %-33s| %-19s| %-13s| %-7s|\n",
                    (!Objects.equals(Integer.toString(user.getId()), "null") && !Objects.equals(Integer.toString(user.getId()), null)) ? user.getId() : "",
                    (!Objects.equals(user.getName(), "null") && !Objects.equals(user.getName(), null)) ? user.getName() : "",
                    (!Objects.equals(user.getSurname(), "null") && !Objects.equals(user.getSurname(), null)) ? user.getSurname() : "",
                    (!Objects.equals(user.getAge(), 0)) ? Integer.toString(user.getAge()) : "",
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    (!Objects.equals(user.getPaymentCode()[0], "null")) ? user.getPaymentCode()[0] : "",
                    (!Objects.equals(user.getPaymentCode()[1], "null")) ? user.getPaymentCode()[1] : "");
        }
        System.out.println("+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");

    }

    public void searchUser(String username) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        User user = userDAO.getUser(username);

        if (user == null) {
            System.out.println("User not found.");
            return;
        }

        System.out.println("\n+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");
        System.out.println("| ID | Name       | Surname    | Age | Username         | Email                            | Password           | CreditCard   | PayPal |");
        System.out.println("+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");
        System.out.printf("| %-3s| %-11s| %-11s| %-4s| %-17s| %-33s| %-19s| %-13s| %-6s |\n",
                (!Objects.equals(Integer.toString(user.getId()), "null") && !Objects.equals(Integer.toString(user.getId()), null)) ? user.getId() : "",
                (!Objects.equals(user.getName(), "null") && !Objects.equals(user.getName(), null)) ? user.getName() : "",
                (!Objects.equals(user.getSurname(), "null") && !Objects.equals(user.getSurname(), null)) ? user.getSurname() : "",
                (!Objects.equals(user.getAge(), 0)) ? Integer.toString(user.getAge()) : "",
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                (!Objects.equals(user.getPaymentCode()[0], "null")) ? user.getPaymentCode()[0] : "",
                (!Objects.equals(user.getPaymentCode()[1], "null")) ? user.getPaymentCode()[1] : "");
        System.out.println("+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");

    }

    public void addUser(String name, String surname, int age, String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) throws SQLException, ClassNotFoundException {

        LoginController loginController = new LoginController();

        loginController.register(name, surname, age, username, email, password, paymentMethod, cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);

        System.out.println("\nUser added successfully.");

    }

    public void removeUser(String username) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        if (userDAO.getUser(username) == null) {
            System.out.println("User not found.");
            return;
        }
        userDAO.removeUser(username);
        System.out.println("User removed successfully.");

    }

}