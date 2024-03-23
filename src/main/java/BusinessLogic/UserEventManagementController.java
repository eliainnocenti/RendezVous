package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.DomainModel.Event;
import main.java.ORM.EventDAO;
import main.java.ORM.ParticipationDAO;
import main.java.ORM.RequestDAO;

import java.sql.SQLException;

public class UserEventManagementController {

    User user;

    public UserEventManagementController(User user) { this.user = user; }

    public void createEvent(String name, String description, String location, String date, String time, boolean refundable, float fee) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        eventDAO.addEvent(name, description, location, date, time, refundable, fee, user.getId());

    }

    public void viewCreatedEvents() throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        System.out.println("\nEvents:");

        System.out.println("\n+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        System.out.println("| Code | Name                                               | Description                                        | Location                                     | Date                | Time                | Refundable | Fee     | Created By   |");
        System.out.println("+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        for (Event event : eventDAO.getEventsByCreator(user.getId()))
            System.out.printf("| %-4s | %-50s | %-50s | %-44s | %-19s | %-19s | %-10s | %-7s | %-12s |\n",
                    event.getCode(),
                    event.getName(),
                    event.getDescription(),
                    event.getLocation(),
                    event.getDate(),
                    event.getTime(),
                    event.isRefundable() ? "Yes" : "No",
                    Float.toString(event.getFee()),
                    Integer.toString(event.getCreatedBy()));
        System.out.println("+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");

    }

    public void viewCreatedEventsAttendees(int code) throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();
        EventDAO eventDAO = new EventDAO();

        if (eventDAO.getEvent(code).getCreatedBy() != user.getId()){
            System.out.println("You are not the creator of the event.");
            return;
        }

        System.out.println("\nAttendees:");

        System.out.println("\n+------+-----------------+-----------------+-------------------------------------+-----------------+-----------------+");
        System.out.println("| ID   | Name            | Surname         | Email                               | Username        | Payment Method  |");
        System.out.println("+------+-----------------+-----------------+-------------------------------------+-----------------+-----------------+");
        for (User user : participationDAO.getParticipants(code))
            System.out.printf("| %-4s | %-15s | %-15s | %-35s | %-15s | %-15s |\n",
                    user.getId(),
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getPaymentMethodType());
        System.out.println("+------+-----------------+-----------------+-------------------------------------+-----------------+-----------------+");

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