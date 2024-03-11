package main.java.ORM;

import main.java.DomainModel.Event;
import main.java.DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAO {

    public void addEvent(String name, String description, String location, String date, String time, boolean refundable, float fee, int created_by) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("INSERT INTO \"Event\" (name, description, location, date, time, refundable, fee, created_by) " +
                                   "VALUES ('%s', '%s', '%s', '%s', '%s', '%b', '%f', '%d')", name, description, location, date, time, refundable, fee, created_by);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void removeEvent(int code) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("DELETE FROM \"Event\" WHERE code = '%d'", code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public Event getEvent(int code) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Event\" WHERE code = '%d'", code);
        Event event = null;

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return event;

    }

    public ArrayList<Event> getEventsByName(String name) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Event\" WHERE name = '%s'", name);

        ArrayList<Event> events = new ArrayList<Event>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return events;

    }

    public ArrayList<Event> getEventsByLocation(String location) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Event\" WHERE location = '%s'", location);

        ArrayList<Event> events = new ArrayList<Event>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return events;

    }

    public ArrayList<Event> getEventsByDate(String date) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Event\" WHERE date = '%s'", date);

        ArrayList<Event> events = new ArrayList<Event>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return events;

    }

    public ArrayList<Event> getEventsByCreator(int created_by) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Event\" WHERE created_by = '%d'", created_by);

        ArrayList<Event> events = new ArrayList<Event>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return events;

    }

    public ArrayList<Event> getAllEvents() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Event\"");

        ArrayList<Event> events = new ArrayList<Event>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return events;

    }

    public ArrayList<Event> getAvailableEvents() throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("SELECT * FROM \"Event\""); // FIXME: add condition

        ArrayList<Event> events = new ArrayList<Event>();

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
            resultSet.close();
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

        return events;

    }

    public ArrayList<User> getEventParticipants(int code) {
        // TODO: implement this method (use the ParticipationTable)

        return null;

    }


    public void updateName(int code, String name) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"Event\" SET name = '%s' WHERE code = '%d'", name, code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateDescription(int code, String description) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"Event\" SET description = '%s' WHERE code = '%d'", description, code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateLocation(int code, String location) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"Event\" SET location = '%s' WHERE code = '%d'", location, code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateDate(int code, String date) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"Event\" SET date = '%s' WHERE code = '%d'", date, code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateTime(int code, String time) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"Event\" SET time = '%s' WHERE code = '%d'", time, code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateRefundable(int code, boolean refundable) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"Event\" SET refundable = '%b' WHERE code = '%d'", refundable, code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

    public void updateFee(int code, float fee) throws SQLException, ClassNotFoundException {

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"Event\" SET fee = '%f' WHERE code = '%d'", fee, code);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println(""); // TODO: add message
        } catch (SQLException e) {
            System.err.println("" + e.getMessage()); // TODO: add message
        }

    }

}