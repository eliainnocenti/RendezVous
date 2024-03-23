package main.java.ORM;

import main.java.DomainModel.CreditCard;
import main.java.DomainModel.PayPal;
import main.java.DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDAO {

    private Connection connection;

    public UserDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void addUser(String name, String surname, int age, String username, String email, String password) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO \"User\" (name, surname, age, username, email, password) " +
                                   "VALUES ('%s', '%s', '%d', '%s', '%s', '%s')", name, surname, age, username, email, password);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void addUser(String username, String email, String password) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO \"User\" (username, email, password) " +
                                   "VALUES ('%s', '%s', '%s')", username, email, password);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void addUser(String name, String surname, int age, String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) throws SQLException, ClassNotFoundException {

        String sql = "";

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("credit card") || paymentMethod.equals("creditcard")) {
            CreditCardDAO creditCardDAO = new CreditCardDAO();
            creditCardDAO.addCreditCard(cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
            sql = String.format("INSERT INTO \"User\" (name, surname, age, username, email, password, creditCard) " +
                                "VALUES ('%s', '%s', '%d', '%s', '%s', '%s', '%s')",
                                name, surname, age, username, email, password, cardNumberORuniqueCode);
        } else if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
            PayPalDAO payPalDAO = new PayPalDAO();
            payPalDAO.addPayPalAccount(cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
            int uniqueCode = payPalDAO.getUniqueCode(cardExpirationDateORaccountEmail);
            sql = String.format("INSERT INTO \"User\" (name, surname, age, username, email, password, paypal) " +
                                "VALUES ('%s', '%s', '%d', '%s', '%s', '%s', '%d')",
                                name, surname, age, username, email, password, uniqueCode);
        }

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void addUser(String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) throws SQLException, ClassNotFoundException {

        String sql = "";

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("credit card") || paymentMethod.equals("creditcard")) {
            CreditCardDAO creditCardDAO = new CreditCardDAO();
            creditCardDAO.addCreditCard(cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
            sql = String.format("INSERT INTO \"User\" (username, email, password, creditCard) " +
                                "VALUES ('%s', '%s', '%s', '%s')",
                                username, email, password, cardNumberORuniqueCode);
        } else if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
            PayPalDAO payPalDAO = new PayPalDAO();
            payPalDAO.addPayPalAccount(cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
            int uniqueCode = payPalDAO.getUniqueCode(cardExpirationDateORaccountEmail);
            sql = String.format("INSERT INTO \"User\" (username, email, password, paypal) " +
                                "VALUES ('%s', '%s', '%s', '%d')",
                                username, email, password, uniqueCode);
        }

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("User added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeUser(String username) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"User\" WHERE username = '%s'", username);

        PreparedStatement preparedStatement = null;

        // remove payment method (CASCADE DELETE)
        removePaymentMethod(username);

        // remove requests (CASCADE DELETE)
        removeRequests(username);

        // remove participations (CASCADE DELETE)
        removeParticipations(username);

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("User removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removePaymentMethod(String username) throws SQLException, ClassNotFoundException {

        String sql;

        PreparedStatement preparedStatement = null;

        try {
            User user = getUser(username);
            if (user.getPaymentMethodType().equals("CreditCard")) {
                // FIXME: creditCard elimination is not working
                CreditCardDAO creditCardDAO = new CreditCardDAO();
                String cardNumber = getCardNumber(username);
                sql = String.format("UPDATE \"User\" SET creditCard = NULL WHERE username = '%s'", username);
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                creditCardDAO.removeCreditCard(cardNumber);
            } else if (user.getPaymentMethodType().equals("PayPal")) {
                PayPalDAO payPalDAO = new PayPalDAO();
                int uniqueCode = getUniqueCode(username);
                sql = String.format("UPDATE \"User\" SET PayPal = NULL WHERE username = '%s'", username);
                preparedStatement = connection.prepareStatement(sql);
                preparedStatement.executeUpdate();
                payPalDAO.removePayPalAccount(uniqueCode);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
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

        User user = null;

        String sql = String.format("SELECT * FROM \"User\" WHERE username = '%s'", username);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                if (resultSet.getString("password").equals(password)) {
                    int id = resultSet.getInt("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    int age = resultSet.getInt("age");
                    String email = resultSet.getString("email");
                    if (resultSet.getString("creditCard") != null) {
                        CreditCard creditCard_tmp = new CreditCardDAO().getCreditCard(resultSet.getString("creditCard"));
                        user = new User(id, name, surname, age, username, email, password, "CreditCard", creditCard_tmp.getCardNumber(), creditCard_tmp.getCardExpirationDate(), creditCard_tmp.getCardSecurityCode());
                    } else if (resultSet.getString("payPal") != null) {
                        PayPal payPal_tmp = new PayPalDAO().getPayPalAccount(Integer.parseInt(resultSet.getString("payPal")));
                        user = new User(id, name, surname, age, username, email, password, "PayPal", Integer.toString(payPal_tmp.getUniqueCode()), payPal_tmp.getAccountEmail(), payPal_tmp.getAccountPassword());
                    } else {
                        user = new User(id, name, surname, age, username, email, password);
                    }
                } else {
                    System.out.println("Invalid password. Please try again.");
                    return null;
                }
            } else {
                System.out.println("Invalid username. Please try again."); // FIXME: it asks the password anyway
                return null;
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return user;

    }

    public User getUser(String username) throws SQLException, ClassNotFoundException {

        User user = null;

        String sql = String.format("SELECT * FROM \"User\" WHERE username = '%s'", username);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                if (resultSet.getString("creditCard") != null) {
                    CreditCard creditCard_tmp = new CreditCardDAO().getCreditCard(resultSet.getString("creditCard"));
                    user = new User(id, name, surname, age, username, email, password, "CreditCard", creditCard_tmp.getCardNumber(), creditCard_tmp.getCardExpirationDate(), creditCard_tmp.getCardSecurityCode());
                } else if (resultSet.getString("PayPal") != null) {
                    PayPal payPal_tmp = new PayPalDAO().getPayPalAccount(Integer.parseInt(resultSet.getString("PayPal")));
                    user = new User(id, name, surname, age, username, email, password, "PayPal", Integer.toString(payPal_tmp.getUniqueCode()), payPal_tmp.getAccountEmail(), payPal_tmp.getAccountPassword());
                } else {
                    user = new User(id, name, surname, age, username, email, password);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return user;

    }

    public User getUser(int id) throws SQLException, ClassNotFoundException {

        User user = null;

        String sql = String.format("SELECT * FROM \"User\" WHERE id = '%d'", id);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                if (resultSet.getString("creditCard") != null) {
                    CreditCard creditCard_tmp = new CreditCardDAO().getCreditCard(resultSet.getString("creditCard"));
                    user = new User(id, name, surname, age, username, email, password, "CreditCard", creditCard_tmp.getCardNumber(), creditCard_tmp.getCardExpirationDate(), creditCard_tmp.getCardSecurityCode());
                } else if (resultSet.getString("PayPal") != null) {
                    PayPal payPal_tmp = new PayPalDAO().getPayPalAccount(Integer.parseInt(resultSet.getString("PayPal")));
                    user = new User(id, name, surname, age, username, email, password, "PayPal", Integer.toString(payPal_tmp.getUniqueCode()), payPal_tmp.getAccountEmail(), payPal_tmp.getAccountPassword());
                } else {
                    user = new User(id, name, surname, age, username, email, password);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return user;

    }

    public String[] getPersonalData(String paymentMethod, String cardNumberORuniqueCode) throws SQLException {

        String[] data = new String[2];

        String sql;

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("credit card") || paymentMethod.equals("creditcard")) {
            sql = String.format("SELECT * FROM \"User\" WHERE creditCard = '%s'", cardNumberORuniqueCode);
        } else if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
            sql = String.format("SELECT * FROM \"User\" WHERE payPal = '%s'", cardNumberORuniqueCode);
        } else {
            return null;
        }

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                data[0] = resultSet.getString("name");
                data[1] = resultSet.getString("surname");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return data;

    }

    public ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {

        ArrayList<User> users = new ArrayList<>();

        String sql = String.format("SELECT * FROM \"User\" ORDER BY id ASC");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String surname = resultSet.getString("surname");
                int age = resultSet.getInt("age");
                String username = resultSet.getString("username");
                String email = resultSet.getString("email");
                String password = resultSet.getString("password");
                if (resultSet.getString("creditCard") != null) {
                    CreditCard creditCard_tmp = new CreditCardDAO().getCreditCard(resultSet.getString("creditCard"));
                    users.add(new User(id, name, surname, age, username, email, password, "CreditCard", creditCard_tmp.getCardNumber(), creditCard_tmp.getCardExpirationDate(), creditCard_tmp.getCardSecurityCode()));
                } else if (resultSet.getString("PayPal") != null) {
                    PayPal payPal_tmp = new PayPalDAO().getPayPalAccount(Integer.parseInt(resultSet.getString("PayPal")));
                    users.add(new User(id, name, surname, age, username, email, password, "PayPal", Integer.toString(payPal_tmp.getUniqueCode()), payPal_tmp.getAccountEmail(), payPal_tmp.getAccountPassword()));
                } else {
                    users.add(new User(id, name, surname, age, username, email, password));
                }
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return users;

    }

    public void updateName(String username, String name) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"User\" SET name = '%s' WHERE username = '%s'", name, username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Name updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateSurname(String username, String surname) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"User\" SET surname = '%s' WHERE username = '%s'", surname, username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Surname updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateAge(String username, int age) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"User\" SET age = '%d' WHERE username = '%s'", age, username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Age updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateUsername(String username, String newUsername) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"User\" SET username = '%s' WHERE username = '%s'", newUsername, username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Username updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateEmail(String username, String email) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"User\" SET email = '%s' WHERE username = '%s'", email, username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Email updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updatePassword(String username, String password) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"User\" SET password = '%s' WHERE username = '%s'", password, username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Password updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateCreditCard(String username, String cardNumber, String cardExpirationDate, String cardSecurityCode) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"User\" SET creditCard = '%s' WHERE username = '%s'", cardNumber, username);

        PreparedStatement preparedStatement = null;

        try {
            CreditCardDAO creditCardDAO = new CreditCardDAO();
            creditCardDAO.addCreditCard(cardNumber, cardExpirationDate, cardSecurityCode);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("CreditCard updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updatePayPal(String username, String accountEmail, String accountPassword) throws SQLException, ClassNotFoundException {

        String sql;

        PreparedStatement preparedStatement = null;

        try {
            PayPalDAO payPalDAO = new PayPalDAO();
            payPalDAO.addPayPalAccount(accountEmail, accountPassword);
            int uniqueCode = payPalDAO.getUniqueCode(accountEmail);
            sql = String.format("UPDATE \"User\" SET payPal = '%s' WHERE username = '%s'", uniqueCode, username);
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("PayPal account updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public ArrayList<String> getAllUsernames() throws SQLException, ClassNotFoundException {

        ArrayList<String> usernames = new ArrayList<>();

        String sql = String.format("SELECT username FROM \"User\" ORDER BY id ASC");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                usernames.add(resultSet.getString("username"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return usernames;
    }

    public ArrayList<String> getAllEmails() throws SQLException, ClassNotFoundException {

        ArrayList<String> emails = new ArrayList<>();

        String sql = String.format("SELECT email FROM \"User\" ORDER BY id ASC");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                emails.add(resultSet.getString("email"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return emails;
    }

    private String getCardNumber(String username) throws SQLException, ClassNotFoundException {

        String cardNumber = null;

        String sql = String.format("SELECT creditCard FROM \"User\" WHERE username = '%s'", username);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                cardNumber = resultSet.getString("creditCard");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return cardNumber;

    }

    private int getUniqueCode(String username) throws SQLException, ClassNotFoundException {

        int uniqueCode = 0;

        String sql = String.format("SELECT payPal FROM \"User\" WHERE username = '%s'", username);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                uniqueCode = resultSet.getInt("payPal");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return uniqueCode;

    }

}