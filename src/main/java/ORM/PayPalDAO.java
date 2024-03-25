package main.java.ORM;

import main.java.DomainModel.PayPal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PayPalDAO {

    private Connection connection;

    public PayPalDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void addPayPalAccount(String email, String password) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO \"PayPal\" (accountEmail, accountPassword) " +
                                   "VALUES ('%s', '%s')" , email, password);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("PayPal account added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removePayPalAccount(int uniqueCode) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"PayPal\" WHERE uniqueCode = '%d'", uniqueCode);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("PayPal account removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removePayPalAccount(String email) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"PayPal\" WHERE accountEmail = '%s'", email);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("PayPal account removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public int getUniqueCode(String email) throws SQLException, ClassNotFoundException {

        int uniqueCode = 0;

        String sql = String.format("SELECT uniqueCode FROM \"PayPal\" WHERE accountEmail = '%s'", email);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                uniqueCode = resultSet.getInt("uniqueCode");
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return uniqueCode;

    }

    public PayPal getPayPalAccountByUser(int userId) throws SQLException, ClassNotFoundException {

        PayPal payPal = null;

        String sql = String.format("SELECT * FROM \"PayPal\" WHERE accountEmail = (SELECT PayPal FROM \"User\" WHERE id = %d)", userId);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String[] data = new UserDAO().getPersonalData("PayPal", resultSet.getString("uniqueCode"));
                payPal = new PayPal(data[0], data[1], Integer.toString(resultSet.getInt("uniqueCode")),  resultSet.getString("accountEmail"), resultSet.getString("accountPassword"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return payPal;

    }

    public PayPal getPayPalAccount(int uniqueCode) throws SQLException, ClassNotFoundException {

        PayPal payPal = null;

        String sql = String.format("SELECT * FROM \"PayPal\" WHERE uniqueCode = '%d'", uniqueCode);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String[] data = new UserDAO().getPersonalData("PayPal", Integer.toString(uniqueCode));
                payPal = new PayPal(data[0], data[1], Integer.toString(uniqueCode), resultSet.getString("accountEmail"), resultSet.getString("accountPassword"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return payPal;

    }

    public PayPal getPayPalAccount(String email) throws SQLException, ClassNotFoundException {

        PayPal payPal = null;

        String sql = String.format("SELECT * FROM \"PayPal\" WHERE accountEmail = '%s'", email);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int uniqueCode = resultSet.getInt("uniqueCode");
                String[] data = new UserDAO().getPersonalData("PayPal", Integer.toString(uniqueCode));
                payPal = new PayPal(data[0], data[1], Integer.toString(uniqueCode), resultSet.getString("accountEmail"), resultSet.getString("accountPassword"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return payPal;

    }

    public ArrayList<PayPal> getAllPayPalAccounts() throws SQLException, ClassNotFoundException {

        ArrayList<PayPal> payPals = new ArrayList<PayPal>();

        String sql = String.format("SELECT * FROM \"PayPal\"");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int uniqueCode = resultSet.getInt("uniqueCode");
                String[] data = new UserDAO().getPersonalData("PayPal", Integer.toString(uniqueCode));
                payPals.add(new PayPal(data[0], data[1], Integer.toString(uniqueCode), resultSet.getString("accountEmail"), resultSet.getString("accountPassword")));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return payPals;

    }

}