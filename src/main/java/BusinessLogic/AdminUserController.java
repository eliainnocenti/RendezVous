package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.ORM.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminUserController {

    public ArrayList<User> viewUsers() throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        return userDAO.getAllUsers();

    }

    public ArrayList<User> searchUser(String username) throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();

        User user = userDAO.getUser(username);

        if (user == null) {
            System.out.println("User not found.");
            return null;
        }

        ArrayList<User> users = new ArrayList<>();

        users.add(user);

        return users;

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