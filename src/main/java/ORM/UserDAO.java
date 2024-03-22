package main.java.ORM;

import main.java.DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    public void addUser(String name, String surname, int age, String username, String email, String password) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"User\" (name, surname, age, username, email, password) " +
                                   "VALUES ('%s', '%s', '%d', '%s', '%s', '%s')", name, surname, age, username, email, password);

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
        String sql = String.format("INSERT INTO \"User\" (username, email, password) " +
                                   "VALUES ('%s', '%s', '%s')", username, email, password);

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
        String sql2;
        String query = "";

        // TODO: extract CreditCardDAO and PayPalDAO methods

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("credit card") || paymentMethod.equals("creditcard")) {
            sql = String.format("INSERT INTO \"CreditCard\" (cardNumber, cardExpirationDate, cardSecurityCode) " +
                                "VALUES ('%s', '%s', '%s');" +
                                "INSERT INTO \"User\" (name, surname, age, username, email, password, creditCard) " +
                                "VALUES ('%s', '%s', '%d', '%s', '%s', '%s', '%s')",
                                cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword,
                                name, surname, age, username, email, password, cardNumberORuniqueCode);
        } else if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
            sql = String.format("INSERT INTO \"PayPal\" (accountEmail, accountPassword) " +
                                "VALUES ('%s', '%s');" +
                                "INSERT INTO \"User\" (name, surname, age, username, email, password) " +
                                "VALUES ('%s', '%s', '%d', '%s', '%s', '%s')",
                                cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword,
                                name, surname, age, username, email, password);
            query = String.format("SELECT uniqueCode FROM \"PayPal\" WHERE accountEmail = '%s' AND accountPassword = '%s'", cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet resultSet = psQuery.executeQuery();
                resultSet.next();
                sql2 = String.format("UPDATE \"User\" SET PayPal = '%s' WHERE username = '%s'", resultSet.getString("uniqueCode"), username);
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
            }
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void addUser(String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = "";
        String sql2;
        String query = "";

        // TODO: extract CreditCardDAO and PayPalDAO methods

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("credit card") || paymentMethod.equals("creditcard")) {
            sql = String.format("INSERT INTO \"CreditCard\" (cardNumber, cardExpirationDate, cardSecurityCode) " +
                            "VALUES ('%s', '%s', '%s');" +
                            "INSERT INTO \"User\" (username, email, password, creditCard) " +
                            "VALUES ('%s', '%s', '%s', '%s')",
                    cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword,
                    username, email, password, cardNumberORuniqueCode);
        } else if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
            sql = String.format("INSERT INTO \"PayPal\" (accountEmail, accountPassword) " +
                            "VALUES ('%s', '%s');" +
                            "INSERT INTO \"User\" (username, email, password) " +
                            "VALUES ('%s', '%s', '%s')",
                    cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword,
                    username, email, password);
            query = String.format("SELECT uniqueCode FROM \"PayPal\" WHERE accountEmail = '%s' AND accountPassword = '%s'", cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
        }

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet resultSet = psQuery.executeQuery();
                resultSet.next();
                sql2 = String.format("UPDATE \"User\" SET PayPal = '%s' WHERE username = '%s'", resultSet.getString("uniqueCode"), username);
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
            }
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeUser(String username) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"User\" WHERE username = '%s'", username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        // remove payment method (CASCADE DELETE)
        removePaymentMethod(username);

        // remove requests (CASCADE DELETE)
        removeRequests(username);

        // remove participations (CASCADE DELETE)
        removeParticipations(username);

    }

    public void removePaymentMethod(String username) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        // TODO: extract CreditCardDAO and PayPalDAO methods

        String query1 = String.format("SELECT creditCard FROM \"User\" WHERE username = '%s'", username);
        String query2 = String.format("SELECT PayPal FROM \"User\" WHERE username = '%s'", username);
        PreparedStatement psQuery1 = connection.prepareStatement(query1);
        PreparedStatement psQuery2 = connection.prepareStatement(query2);
        ResultSet rsQuery1 = psQuery1.executeQuery();
        ResultSet rsQuery2 = psQuery2.executeQuery();

        if (rsQuery1.next() && rsQuery1.getString("creditCard") != null) {
            String sql = String.format("UPDATE \"User\" SET creditCard = NULL WHERE username = '%s'", username);
            String sql1 = String.format("DELETE FROM \"CreditCard\" WHERE cardNumber = '%s'", rsQuery1.getString("creditCard"));
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
                preparedStatement1.executeUpdate();
                preparedStatement1.close();
            } catch (SQLException e) {
                System.err.println("" + e.getMessage()); // TODO: add message
            }
        } else if (rsQuery2.next() && rsQuery2.getString("PayPal") != null) {
            String sql = String.format("UPDATE \"User\" SET PayPal = NULL WHERE username = '%s'", username);
            String sql2 = String.format("DELETE FROM \"PayPal\" WHERE uniqueCode = '%s'", rsQuery2.getString("PayPal"));
            try {
                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                preparedStatement.close();
                PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
                preparedStatement2.executeUpdate();
                preparedStatement2.close();
            } catch (SQLException e) {
                System.err.println("" + e.getMessage()); // TODO: add message
            }
        }

    }

    public void removeRequests(String username) throws SQLException, ClassNotFoundException {

        RequestDAO requestDAO = new RequestDAO();

        requestDAO.removeAllRequestsByUser(username);

    }

    public void removeParticipations(String username) throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        participationDAO.removeAllParticipationsByUser(username);

    }

    public User checkPassword(String username, String password) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"User\" WHERE username = '%s'", username);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // TODO: extract CreditCardDAO and PayPalDAO methods

        if (resultSet.next()) {
            if (resultSet.getString("password").equals(password)) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                if (resultSet.getString("creditCard") != null) {
                    String query = String.format("SELECT cardNumber, cardExpirationDate, cardSecurityCode FROM \"CreditCard\" WHERE cardNumber = '%s'", resultSet.getString("creditCard"));
                    PreparedStatement psQuery = connection.prepareStatement(query);
                    ResultSet rsQuery = psQuery.executeQuery();
                    rsQuery.next();
                    return new User(id, name, surname, age, username, email, password, "CreditCard", rsQuery.getString("cardNumber"), rsQuery.getString("cardExpirationDate"), rsQuery.getString("cardSecurityCode"));
                } else if (resultSet.getString("PayPal") != null) {
                    String query = String.format("SELECT uniqueCode, accountEmail, accountPassword FROM \"PayPal\" WHERE uniqueCode = '%s'", resultSet.getString("PayPal"));
                    PreparedStatement psQuery = connection.prepareStatement(query);
                    ResultSet rsQuery = psQuery.executeQuery();
                    rsQuery.next();
                    return new User(id, name, surname, age, username, email, password, "PayPal", rsQuery.getString("uniqueCode"), rsQuery.getString("accountEmail"), rsQuery.getString("accountPassword"));
                } else {
                    return new User(id, name, surname, age, username, email, password);
                }
            } else {
                System.out.println("Invalid password. Please try again.");
                return null;
            }
        } else {
            System.out.println("Invalid username. Please try again."); // FIXME: it asks the password anyway
            return null;
        }

    }

    public User getUser(String username) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"User\" WHERE username = '%s'", username);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // TODO: extract CreditCardDAO and PayPalDAO methods

        if (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = resultSet.getInt("age");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            if (resultSet.getString("creditCard") != null) {
                String query = String.format("SELECT cardNumber, cardExpirationDate, cardSecurityCode FROM \"CreditCard\" WHERE cardNumber = '%s'", resultSet.getString("creditCard"));
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                return new User(id, name, surname, age, username, email, password, "CreditCard", rsQuery.getString("cardNumber"), rsQuery.getString("cardExpirationDate"), rsQuery.getString("cardSecurityCode"));
            } else if (resultSet.getString("PayPal") != null) {
                String query = String.format("SELECT uniqueCode, accountEmail, accountPassword FROM \"PayPal\" WHERE uniqueCode = '%s'", resultSet.getString("PayPal"));
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                return new User(id, name, surname, age, username, email, password, "PayPal", rsQuery.getString("uniqueCode"), rsQuery.getString("accountEmail"), rsQuery.getString("accountPassword"));
            } else {
                return new User(id, name, surname, age, username, email, password);
            }

        }

        return null;
    }

    public User getUser(int id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"User\" WHERE id = %d", id);

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // TODO: extract CreditCardDAO and PayPalDAO methods

        if (resultSet.next()) {

            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = resultSet.getInt("age");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            if (resultSet.getString("creditCard") != null) {
                String query = String.format("SELECT cardNumber, cardExpirationDate, cardSecurityCode FROM \"CreditCard\" WHERE cardNumber = '%s'", resultSet.getString("creditCard"));
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                return new User(id, name, surname, age, username, email, password, "CreditCard", rsQuery.getString("cardNumber"), rsQuery.getString("cardExpirationDate"), rsQuery.getString("cardSecurityCode"));
            } else if (resultSet.getString("PayPal") != null) {
                String query = String.format("SELECT uniqueCode, accountEmail, accountPassword FROM \"PayPal\" WHERE uniqueCode = '%s'", resultSet.getString("PayPal"));
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                return new User(id, name, surname, age, username, email, password, "PayPal", rsQuery.getString("uniqueCode"), rsQuery.getString("accountEmail"), rsQuery.getString("accountPassword"));
            } else {
                return new User(id, name, surname, age, username, email, password);
            }

        }

        return null;
    }

    public ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        ArrayList<User> users = new ArrayList<>();

        String sql = String.format("SELECT * FROM \"User\" ORDER BY id ASC");
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        // TODO: extract CreditCardDAO and PayPalDAO methods

        while (resultSet.next()) {

            int id = resultSet.getInt("id");
            String name = resultSet.getString("name");
            String surname = resultSet.getString("surname");
            int age = resultSet.getInt("age");
            String username = resultSet.getString("username");
            String email = resultSet.getString("email");
            String password = resultSet.getString("password");

            if (resultSet.getString("creditCard") != null) {
                String query = String.format("SELECT cardNumber, cardExpirationDate, cardSecurityCode FROM \"CreditCard\" WHERE cardNumber = '%s'", resultSet.getString("creditCard"));
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                users.add(new User(id, name, surname, age, username, email, password, "CreditCard", rsQuery.getString("cardNumber"), rsQuery.getString("cardExpirationDate"), rsQuery.getString("cardSecurityCode")));
            } else if (resultSet.getString("PayPal") != null) {
                String query = String.format("SELECT uniqueCode, accountEmail, accountPassword FROM \"PayPal\" WHERE uniqueCode = '%s'", resultSet.getString("PayPal"));
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                users.add(new User(id, name, surname, age, username, email, password, "PayPal", rsQuery.getString("uniqueCode"), rsQuery.getString("accountEmail"), rsQuery.getString("accountPassword")));
            } else {
                users.add(new User(id, name, surname, age, username, email, password));
            }

        }

        return users;
    }

    public void updateName(String username, String name) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        String sql = String.format("UPDATE \"User\" SET name = '%s' WHERE username = '%s'", name, username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateSurname(String username, String surname) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        String sql = String.format("UPDATE \"User\" SET surname = '%s' WHERE username = '%s'", surname, username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateAge(String username, int age) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        String sql = String.format("UPDATE \"User\" SET age = '%d' WHERE username = '%s'", age, username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateUsername(String username, String newUsername) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        String sql1 = String.format("UPDATE \"User\" SET username = '%s' WHERE username = '%s'", newUsername, username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql1);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateEmail(String username, String email) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        String sql = String.format("UPDATE \"User\" SET email = '%s' WHERE username = '%s'", email, username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updatePassword(String username, String password) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        String sql = String.format("UPDATE \"User\" SET password = '%s' WHERE username = '%s'", password, username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateCreditCard(String username, String cardNumber, String cardExpirationDate, String cardSecurityCode) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        // TODO: extract CreditCardDAO methods

        String sql1 = String.format("INSERT INTO \"CreditCard\" (cardNumber, cardExpirationDate, cardSecurityCode) " +
                " VALUES ('%s', '%s', '%s')", cardNumber, cardExpirationDate, cardSecurityCode);

        String sql2 = String.format("UPDATE \"User\" SET creditCard = '%s' WHERE username = '%s'", cardNumber, username);

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updatePayPal(String username, String accountEmail, String accountPassword) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();

        // TODO: extract PayPalDAO methods

        String sql1 = String.format("INSERT INTO \"PayPal\" (accountEmail, accountPassword) " +
                " VALUES ('%s', '%s')", accountEmail, accountPassword);

        String query = String.format("SELECT uniqueCode FROM \"PayPal\" WHERE accountEmail = '%s'", accountEmail);

        try {
            PreparedStatement preparedStatement1 = connection.prepareStatement(sql1);
            preparedStatement1.executeUpdate();
            preparedStatement1.close();
            PreparedStatement psQuery = connection.prepareStatement(query);
            ResultSet resultSet = psQuery.executeQuery();
            resultSet.next();
            String sql2 = String.format("UPDATE \"User\" SET payPal = '%s' WHERE username = '%s'", resultSet.getString("uniqueCode"), username);
            PreparedStatement preparedStatement2 = connection.prepareStatement(sql2);
            preparedStatement2.executeUpdate();
            preparedStatement2.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public ArrayList<String> getAllUsernames() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        ArrayList<String> usernames = new ArrayList<>();

        String sql = String.format("SELECT username FROM \"User\" ORDER BY id ASC");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return usernames;
    }

    public ArrayList<String> getAllEmails() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        ArrayList<String> emails = new ArrayList<>();

        String sql = String.format("SELECT email FROM \"User\" ORDER BY id ASC");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                emails.add(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return emails;
    }

}