package main.java.ORM;

import main.java.DomainModel.PayPal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PayPalDAO {

    public void addPayPalAccount(String email, String password) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"PayPal\" (email, password) " +
                                   "VALUES ('%s', '%s')" , email, password);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removePayPalAccount(String email) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"PayPal\" WHERE email = '%s'", email);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removePayPalAccount(int userId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"PayPal\" WHERE uniqueCode = (SELECT PayPal FROM \"User\" WHERE id = %d)", userId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public PayPal getPayPalAccount(int userId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"PayPal\" WHERE accountEmail = (SELECT PayPal FROM \"User\" WHERE id = %d)", userId);
        PayPal payPal = null;

        String query = String.format("SELECT * FROM \"User\" WHERE id = %d", userId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            PreparedStatement psQuery = connection.prepareStatement(query);
            ResultSet rsQuery = psQuery.executeQuery();
            if (resultSet.next()) {
                payPal = new PayPal(rsQuery.getString("name"), rsQuery.getString("surname"), Integer.toString(resultSet.getInt("uniqueCode")),  resultSet.getString("accountEmail"), resultSet.getString("accountPassword"));
            }
            preparedStatement.close();
            psQuery.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return payPal;

    }

    public PayPal getPayPalAccount(String email) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"PayPal\" WHERE accountEmail = '%s'", email);
        PayPal payPal = null;

        String uniqueCode = "";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                uniqueCode = resultSet.getString("uniqueCode");
                String query = String.format("SELECT * FROM \"User\" WHERE PayPal = '%s'", uniqueCode);
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                payPal = new PayPal(rsQuery.getString("name"), rsQuery.getString("surname"), uniqueCode, resultSet.getString("accountEmail"), resultSet.getString("accountPassword"));
                psQuery.close();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return payPal;

    }

    public ArrayList<PayPal> getAllPayPalAccounts() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"PayPal\"");
        ArrayList<PayPal> payPals = new ArrayList<PayPal>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String uniqueCode = resultSet.getString("uniqueCode");
                String query = String.format("SELECT * FROM \"User\" WHERE PayPal = '%s'", uniqueCode);
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                payPals.add(new PayPal(rsQuery.getString("name"), rsQuery.getString("surname"), uniqueCode, resultSet.getString("accountEmail"), resultSet.getString("accountPassword")));
                psQuery.close();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return payPals;

    }

}