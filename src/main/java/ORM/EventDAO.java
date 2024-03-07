package main.java.ORM;

import main.java.DomainModel.Event;
import main.java.DomainModel.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EventDAO {

    public void addEvent(String name, String description, String location, String date, String time, boolean refundable, float fee) {
        // TODO: implement this method

    }

    public void removeEvent(int code) {
        //  TODO: implement this method

    }

    public Event getEvent(int code) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Event> getEventsByName(String name) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Event> getEventsByLocation(String location) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Event> getEventsByDate(String date) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Event> getAllEvents() {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Event> getAvailableEvents() {
        // TODO: implement this method

        return null;

    }

    public ArrayList<User> getEventParticipants(int code) {
        // TODO: implement this method

        return null;

    }

    // update event methods // TODO: implement these methods

}