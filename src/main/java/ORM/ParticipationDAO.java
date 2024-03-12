package main.java.ORM;

import main.java.DomainModel.Event;
import main.java.DomainModel.Participation;
import main.java.DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParticipationDAO {

    public void addParticipation(int userId, int eventId, String paymentMethod) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"Participation\" (user_id, event_id, paymentMethod) " +
                                   "VALUES (%d, %d, '%s')", userId, eventId, paymentMethod);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void addParticipation(String username, int eventId, String paymentMethod) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"Participation\" (user_id, event_id, paymentMethod) " +
                                   "VALUES ((SELECT id FROM \"User\" WHERE username = '%s'), %d, '%s')", username, eventId, paymentMethod);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeParticipation(int userId, int eventId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = %d AND event_id = %d", userId, eventId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeParticipation(String username, int eventId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s') AND event_id = %d", username, eventId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeAllParticipationsByUser(int userId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = %d", userId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeAllParticipationsByUser(String username) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s')", username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeAllParticipationsByEvent(int eventId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"Participation\" WHERE event_id = %d", eventId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

    }

    public Participation getParticipation(int userId, int eventId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = %d AND event_id = %d", userId, eventId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                return new Participation(user, event, resultSet.getString("paymentMethod"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return null;

    }

    public Participation getParticipation(String username, int eventId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s') AND event_id = %d", username, eventId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                return new Participation(user, event, resultSet.getString("paymentMethod"));
            }
            preparedStatement.close();
        } catch (SQLException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return null;

    }

    public ArrayList<Participation> getParticipationsByUser(int userId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = %d", userId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Participation> participations = new ArrayList<Participation>();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
            preparedStatement.close();
            return participations;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return null;

    }

    public ArrayList<Participation> getParticipationsByUser(String username) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s')", username);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Participation> participations = new ArrayList<Participation>();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
            preparedStatement.close();
            return participations;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return null;

    }

    public ArrayList<Participation> getParticipationsByEvent(int eventId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Participation\" WHERE event_id = %d", eventId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Participation> participations = new ArrayList<Participation>();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
            preparedStatement.close();
            return participations;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return null;

    }

    public ArrayList<Participation> getAllParticipations() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Participation\"");

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Participation> participations = new ArrayList<Participation>();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
            preparedStatement.close();
            return participations;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return null;

    }

    public ArrayList<User> getParticipants(int eventId) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Participation\" WHERE event_id = %d", eventId);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<User> participants = new ArrayList<User>();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                participants.add(user);
            }
            preparedStatement.close();
            return participants;
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("" + e.getMessage()); // TODO: add message
        }

        return null;

    }

}