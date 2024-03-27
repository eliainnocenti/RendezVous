package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.DomainModel.Event;
import main.java.ORM.EventDAO;
import main.java.ORM.ParticipationDAO;
import main.java.ORM.RequestDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserEventManagementController {

    User user;

    public UserEventManagementController(User user) { this.user = user; }

    public void createEvent(String name, String description, String location, String date, String time, boolean refundable, float fee) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        eventDAO.addEvent(name, description, location, date, time, refundable, fee, user.getId());

    }

    public ArrayList<Event> viewCreatedEvents() throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        return eventDAO.getEventsByCreator(user.getId());

    }

    public ArrayList<User> viewCreatedEventsAttendees(int code) throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();
        EventDAO eventDAO = new EventDAO();

        if (eventDAO.getEvent(code).getCreatedBy() != user.getId()){
            System.out.println("You are not the creator of the event.");
            return null;
        }

        return participationDAO.getParticipants(code);

    }

    public void updateAnEventDescription(int code, String description) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        eventDAO.updateDescription(code, description);

        System.out.println("Description updated.");

    }

    public void requestToChangeAnEventAttributes(int code, String request) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();
        RequestDAO requestDAO = new RequestDAO();

        if (eventDAO.getEvent(code).getCreatedBy() != user.getId()){
            System.out.println("You are not the creator of the event.");
            return;
        }

        requestDAO.addRequest(user.getId(), request);

        System.out.println("Request sent.");

    }

    public void requestToCancelAnEvent(int code) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();
        RequestDAO requestDAO = new RequestDAO();

        if (eventDAO.getEvent(code).getCreatedBy() != user.getId()){
            System.out.println("You are not the creator of the event.");
            return;
        }

        String request = "| CANCEL | Event Code: " + code + " |";

        requestDAO.addRequest(user.getId(), request);

        System.out.println("Request sent.");

    }

}