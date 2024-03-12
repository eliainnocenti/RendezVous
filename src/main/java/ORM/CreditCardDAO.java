package main.java.ORM;

import main.java.DomainModel.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditCardDAO {

    public void addCreditCard(String cardNumber, String cardExpirationDate, String cardSecurityCode) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"CreditCard\" (cardNumber, cardExpirationDate, cardSecurityCode) " +
                                   "VALUES ('%s', '%s', '%s')", cardNumber, cardExpirationDate, cardSecurityCode);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeCreditCard(String cardNumber) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"CreditCard\" WHERE cardNumber = '%s'", cardNumber);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public CreditCard getCreditCard(String cardNumber) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"CreditCard\" WHERE cardNumber = '%s'", cardNumber);
        CreditCard creditCard = null;

        String query = String.format("SELECT * FROM \"User\" WHERE CreditCard = '%s'", cardNumber);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            PreparedStatement psQuery = connection.prepareStatement(query);
            ResultSet rsQuery = psQuery.executeQuery();
            if (resultSet.next()) {
                rsQuery.next();
                creditCard = new CreditCard(rsQuery.getString("name"), rsQuery.getString("surname"), resultSet.getString("cardNumber"), resultSet.getString("cardExpirationDate"), resultSet.getString("cardSecurityCode"));
            }
            preparedStatement.close();
            psQuery.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return creditCard;

    }

    public CreditCard getCreditCard(int userId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"CreditCard\" WHERE cardNumber = (SELECT CreditCard FROM \"User\" WHERE id = %d)", userId);
        CreditCard creditCard = null;

        String query = String.format("SELECT * FROM \"User\" WHERE id = %d", userId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            PreparedStatement psQuery = connection.prepareStatement(query);
            ResultSet rsQuery = psQuery.executeQuery();
            if (resultSet.next()) {
                creditCard = new CreditCard(rsQuery.getString("name"), rsQuery.getString("surname"), resultSet.getString("cardNumber"), resultSet.getString("cardExpirationDate"), resultSet.getString("cardSecurityCode"));
            }
            preparedStatement.close();
            psQuery.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return creditCard;

    }

    public ArrayList<CreditCard> getAllCreditCards() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"CreditCard\"");
        ArrayList<CreditCard> creditCards = new ArrayList<CreditCard>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String cardNumber = resultSet.getString("cardNumber");
                String query = String.format("SELECT * FROM \"User\" WHERE CreditCard = '%s'", cardNumber);
                PreparedStatement psQuery = connection.prepareStatement(query);
                ResultSet rsQuery = psQuery.executeQuery();
                rsQuery.next();
                creditCards.add(new CreditCard(rsQuery.getString("name"), rsQuery.getString("surname"), cardNumber, resultSet.getString("cardExpirationDate"), resultSet.getString("cardSecurityCode")));
                psQuery.close();
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return creditCards;

    }

}