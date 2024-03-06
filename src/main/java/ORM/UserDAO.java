package main.java.ORM;

import main.java.DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

    public void addUser(String name, String surname, int age, String username, String email, String password) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"User\" (name, surname, age, username, email, password) VALUES " +
                                   "('%s', '%s', '%d', '%s', '%s', '%s')", name, surname, age, username, email, password);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }
    }

    public void addUser(String username, String email, String password) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"User\" (username, email, password) VALUES " +
                                   "('%s', '%s', '%s')", username, email, password);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }
    }

    public void addUser(String name, String surname, int age, String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        String sql = "";
        if (paymentMethod.equals("CreditCard")) {
            sql = String.format(""); // TODO
        } else if (paymentMethod.equals("PayPal")) {
            sql = String.format(""); // TODO
        }
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }
    }

    public void addUser(String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) throws SQLException, ClassNotFoundException {

    }

    public void removeUser(String username) throws SQLException, ClassNotFoundException {

    }

    public User getUser(String username) throws SQLException, ClassNotFoundException {
        return null;
    }

    public User checkPassword(String username, String password) throws SQLException, ClassNotFoundException {
        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"User\" WHERE username = '%s'", username);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            if (resultSet.getString("password").equals(password)) {
                // FIXME: payment method
                return new User(resultSet.getString("name"), resultSet.getString("surname"),
                        resultSet.getInt("age") ,resultSet.getString("username"), resultSet.getString("email"), resultSet.getString("password"));
            } else {
                System.out.println("Invalid password. Please try again.");
                return null;
            }
        } else {
            System.out.println("Invalid username. Please try again.");
            return null;
        }
    }

    // update user

}