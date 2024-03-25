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

    private Connection connection;

    public ParticipationDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void addParticipation(int userId, int eventId, String paymentMethod) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO \"Participation\" (user_id, event_id, paymentMethod) " +
                                   "VALUES (%d, %d, '%s')", userId, eventId, paymentMethod);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participation added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void addParticipation(String username, int eventId, String paymentMethod) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO \"Participation\" (user_id, event_id, paymentMethod) " +
                                   "VALUES ((SELECT id FROM \"User\" WHERE username = '%s'), %d, '%s')", username, eventId, paymentMethod);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participation added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeParticipation(int userId, int eventId) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = %d AND event_id = %d", userId, eventId);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participation removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeParticipation(String username, int eventId) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s') AND event_id = %d", username, eventId);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participation removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllParticipationsByUser(int userId) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = %d", userId);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllParticipationsByUser(String username) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s')", username);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeAllParticipationsByEvent(int eventId) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Participation\" WHERE event_id = %d", eventId);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Participations removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public Participation getParticipation(int userId, int eventId) throws SQLException, ClassNotFoundException {

        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = %d AND event_id = %d", userId, eventId);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                return new Participation(user, event, resultSet.getString("paymentMethod"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return null;

    }

    public Participation getParticipation(String username, int eventId) throws SQLException, ClassNotFoundException {

        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s') AND event_id = %d", username, eventId);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                return new Participation(user, event, resultSet.getString("paymentMethod"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return null;

    }

    public ArrayList<Participation> getParticipationsByUser(int userId) throws SQLException, ClassNotFoundException {

        ArrayList<Participation> participations = new ArrayList<Participation>();

        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = %d", userId);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        if (participations.isEmpty()) { return null; }

        return participations;

    }

    public ArrayList<Participation> getParticipationsByUser(String username) throws SQLException, ClassNotFoundException {

        ArrayList<Participation> participations = new ArrayList<Participation>();

        String sql = String.format("SELECT * FROM \"Participation\" WHERE user_id = (SELECT id FROM \"User\" WHERE username = '%s')", username);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        if (participations.isEmpty()) { return null; }

        return participations;

    }

    public ArrayList<Participation> getParticipationsByEvent(int eventId) throws SQLException, ClassNotFoundException {

        ArrayList<Participation> participations = new ArrayList<Participation>();

        String sql = String.format("SELECT * FROM \"Participation\" WHERE event_id = %d", eventId);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        if (participations.isEmpty()) { return null; }

        return participations;

    }

    public ArrayList<Participation> getAllParticipations() throws SQLException, ClassNotFoundException {

        ArrayList<Participation> participations = new ArrayList<Participation>();

        String sql = String.format("SELECT * FROM \"Participation\" ORDER BY event_id, user_id");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                Event event = new EventDAO().getEvent(resultSet.getInt("event_id"));
                participations.add(new Participation(user, event, resultSet.getString("paymentMethod")));
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        if (participations.isEmpty()) { return null; }

        return participations;

    }

    public ArrayList<User> getParticipants(int eventId) throws SQLException, ClassNotFoundException {

        ArrayList<User> participants = new ArrayList<User>();

        String sql = String.format("SELECT * FROM \"Participation\" WHERE event_id = %d", eventId);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = new UserDAO().getUser(resultSet.getInt("user_id"));
                participants.add(user);
            }
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        if (participants.isEmpty()) { return null; }

        return participants;

    }

}