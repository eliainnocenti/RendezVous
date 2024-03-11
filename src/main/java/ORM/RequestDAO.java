package main.java.ORM;

import main.java.DomainModel.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequestDAO {

    public void addRequest(int user_id, String description) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"Request\" (user_id, description) " +
                                   "VALUES ('%d', '%s')", user_id, description);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.err.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeRequest(int user_id, String timestamp) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"Request\" " +
                                   "WHERE user_id = '%d' AND timestamp = '%s'", user_id, timestamp);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.err.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public Request getRequest(int user_id, String timestamp) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Request\" " +
                                   "WHERE user_id = '%d' AND timestamp = '%s'", user_id, timestamp);
        Request request = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                request = new Request(resultSet.getInt("user_id"), resultSet.getString("timestamp"), resultSet.getString("description"));
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return request;

    }

    public ArrayList<Request> getRequestsByUser(int user_id) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Request\" " +
                                   "WHERE user_id = '%d'", user_id);
        ArrayList<Request> requests = new ArrayList<Request>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requests.add(new Request(resultSet.getInt("user_id"), resultSet.getString("timestamp"), resultSet.getString("description")));
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return requests;

    }

    public ArrayList<Request> getAllRequests() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Request\" ORDER BY created_at DESC");
        ArrayList<Request> requests = new ArrayList<Request>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requests.add(new Request(resultSet.getInt("user_id"), resultSet.getString("timestamp"), resultSet.getString("description")));
            }
            connection.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return requests;

    }

}