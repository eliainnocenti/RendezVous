package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.ORM.UserDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminUsersController {

    public void viewUsers() throws SQLException, ClassNotFoundException {

        UserDAO userDAO = new UserDAO();
        ArrayList<User> allUsers = userDAO.getAllUsers();

        // TODO: display users

    }

    public void searchUser() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("Username: ");
        String username = scanner.nextLine();

        User tmp = userDAO.getUser(username);

        // TODO: display user info

    }

    public void addUser() throws SQLException, ClassNotFoundException {

        LoginController loginController = new LoginController();

        loginController.register();
        System.out.println("User added successfully.");

    }

    public void removeUser() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserDAO userDAO = new UserDAO();

        System.out.println("Username: ");
        String username = scanner.nextLine();

        userDAO.removeUser(username);
        System.out.println("User removed successfully.");

    }

}
