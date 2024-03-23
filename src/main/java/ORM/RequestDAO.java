package main.java.ORM;

import main.java.DomainModel.Request;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RequestDAO {

    private Connection connection;

    public RequestDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void addRequest(int user_id, String description) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO \"Request\" (user_id, description) " +
                                   "VALUES ('%d', '%s')", user_id, description);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Request added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeRequest(int code) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Request\" " +
                                   "WHERE code = '%d'", code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Request removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeRequest(int user_id, String created_at) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Request\" " +
                                   "WHERE user_id = '%d' AND created_at = '%s'", user_id, created_at);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Request removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllRequests() throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Request\"");

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Requests removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllRequestsByUser(int user_id) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Request\" " +
                                   "WHERE user_id = '%d'", user_id);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Requests removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllRequestsByUser(String username) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Request\" " +
                                   "WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s')", username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Requests removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public Request getRequest(int code) throws SQLException, ClassNotFoundException {

        Request request = null;

        String sql = String.format("SELECT * FROM \"Request\" WHERE code = '%d'", code);

        PreparedStatement  preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                request = new Request(resultSet.getInt("user_id"), resultSet.getString("created_at"), resultSet.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return request;

    }

    public Request getRequest(int user_id, String created_at) throws SQLException, ClassNotFoundException {

        Request request = null;

        String sql = String.format("SELECT * FROM \"Request\" " +
                                   "WHERE user_id = '%d' AND created_at = '%s'", user_id, created_at);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                request = new Request(resultSet.getInt("user_id"), resultSet.getString("created_at"), resultSet.getString("description"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return request;

    }

    public ArrayList<Request> getRequestsByUser(int user_id) throws SQLException, ClassNotFoundException {

        ArrayList<Request> requests = new ArrayList<Request>();

        String sql = String.format("SELECT * FROM \"Request\" " +
                                   "WHERE user_id = '%d'", user_id);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requests.add(new Request(resultSet.getInt("user_id"), resultSet.getString("created_at"), resultSet.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return requests;

    }

    public ArrayList<Request> getAllRequests() throws SQLException, ClassNotFoundException {

        ArrayList<Request> requests = new ArrayList<Request>();

        String sql = String.format("SELECT * FROM \"Request\" ORDER BY created_at DESC");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                requests.add(new Request(resultSet.getInt("user_id"), resultSet.getString("created_at"), resultSet.getString("description")));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return requests;

    }

}