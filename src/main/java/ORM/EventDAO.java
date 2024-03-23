package main.java.ORM;

import main.java.DomainModel.Event;
import main.java.DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAO {

    private Connection connection;

    public EventDAO() {

        try {
            this.connection = ConnectionManager.getInstance().getConnection();
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
        }

    }

    public void addEvent(String name, String description, String location, String date, String time, boolean refundable, float fee, int created_by) throws SQLException, ClassNotFoundException {

        String sql = String.format("INSERT INTO \"Event\" (name, description, location, date, time, refundable, fee, created_by) " +
                                   "VALUES ('%s', '%s', '%s', '%s', '%s', '%b', '%f', '%d')", name, description, location, date, time, refundable, fee, created_by);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Event added successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void removeEvent(int code) throws SQLException, ClassNotFoundException {

        String sql = String.format("DELETE FROM \"Event\" WHERE code = '%d'", code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Event removed successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public Event getEvent(int code) throws SQLException, ClassNotFoundException {

        Event event = null;

        String sql = String.format("SELECT * FROM \"Event\" WHERE code = '%d'", code);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return event;

    }

    public ArrayList<Event> getEventsByName(String name) throws SQLException, ClassNotFoundException {

        ArrayList<Event> events = new ArrayList<Event>();

        String sql = String.format("SELECT * FROM \"Event\" WHERE name = '%s'", name);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return events;

    }

    public ArrayList<Event> getEventsByLocation(String location) throws SQLException, ClassNotFoundException {

        ArrayList<Event> events = new ArrayList<Event>();

        String sql = String.format("SELECT * FROM \"Event\" WHERE location = '%s'", location);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return events;

    }

    public ArrayList<Event> getEventsByDate(String date) throws SQLException, ClassNotFoundException {

        ArrayList<Event> events = new ArrayList<Event>();

        String sql = String.format("SELECT * FROM \"Event\" WHERE date = '%s'", date);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return events;

    }

    public ArrayList<Event> getEventsByCreator(int created_by) throws SQLException, ClassNotFoundException {

        ArrayList<Event> events = new ArrayList<Event>();

        String sql = String.format("SELECT * FROM \"Event\" WHERE created_by = '%d'", created_by);

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return events;

    }

    public ArrayList<Event> getAllEvents() throws SQLException, ClassNotFoundException {

        ArrayList<Event> events = new ArrayList<Event>();

        String sql = String.format("SELECT * FROM \"Event\"");

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return events;

    }

    public ArrayList<Event> getAvailableEvents() throws SQLException, ClassNotFoundException {

        ArrayList<Event> events = new ArrayList<Event>();

        String sql = String.format("SELECT * FROM \"Event\""); // FIXME: add condition

        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Event event = new Event(resultSet.getString("name"), resultSet.getString("description"), resultSet.getString("location"), resultSet.getString("date"), resultSet.getString("time"), resultSet.getBoolean("refundable"), resultSet.getFloat("fee"), resultSet.getInt("created_by"));
                event.setCode(resultSet.getInt("code"));
                events.add(event);
            }
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
            if (resultSet != null) { resultSet.close(); }
        }

        return events;

    }

    public ArrayList<User> getParticipants(int code) throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        return participationDAO.getParticipants(code);

    }


    public void updateName(int code, String name) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"Event\" SET name = '%s' WHERE code = '%d'", name, code);

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

    public void updateDescription(int code, String description) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"Event\" SET description = '%s' WHERE code = '%d'", description, code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Description updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateLocation(int code, String location) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"Event\" SET location = '%s' WHERE code = '%d'", location, code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Location updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateDate(int code, String date) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"Event\" SET date = '%s' WHERE code = '%d'", date, code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Date updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateTime(int code, String time) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"Event\" SET time = '%s' WHERE code = '%d'", time, code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Time updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateRefundable(int code, boolean refundable) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"Event\" SET refundable = '%b' WHERE code = '%d'", refundable, code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Refundable updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

    public void updateFee(int code, float fee) throws SQLException, ClassNotFoundException {

        String sql = String.format("UPDATE \"Event\" SET fee = '%f' WHERE code = '%d'", fee, code);

        PreparedStatement preparedStatement = null;

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            System.out.println("Fee updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (preparedStatement != null) { preparedStatement.close(); }
        }

    }

}