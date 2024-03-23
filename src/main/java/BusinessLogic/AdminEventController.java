package main.java.BusinessLogic;

import main.java.DomainModel.Event;
import main.java.DomainModel.Participation;
import main.java.DomainModel.Request;
import main.java.ORM.EventDAO;
import main.java.ORM.ParticipationDAO;
import main.java.ORM.RequestDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdminEventController {

    public void seeRequests() throws SQLException, ClassNotFoundException {

        RequestDAO requestDAO = new RequestDAO();

        ArrayList<Request> requests = requestDAO.getAllRequests();

        System.out.println("\n+---------+------------------------------------------------------------------------------------------------------+----------------------------+");
        System.out.println("| User ID | Description                                                                                          | Created At                 |");
        System.out.println("+---------+------------------------------------------------------------------------------------------------------+----------------------------+");
        for (Request request : requests) {
            System.out.printf("| %-7s | %-100s | %-19s |\n",
                    request.getUserId(),
                    request.getDescription(),
                    request.getCreated_at());
        }
        System.out.println("+---------+------------------------------------------------------------------------------------------------------+----------------------------+");

    }

    public void removeRequest(int code) throws SQLException, ClassNotFoundException {

        RequestDAO requestDAO = new RequestDAO();

        requestDAO.removeRequest(code);

        System.out.println("Request removed.");

    }

    public void removeAllRequests() throws SQLException, ClassNotFoundException {

        RequestDAO requestDAO = new RequestDAO();

        requestDAO.removeAllRequests();

        System.out.println("All requests removed.");

    }

    public void viewEvents() throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        ArrayList<Event> allEvents = eventDAO.getAllEvents();

        System.out.println("\n+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        System.out.println("| Code | Name                                               | Description                                        | Location                                     | Date                | Time                | Refundable | Fee     | Created By   |");
        System.out.println("+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        for (Event event : allEvents)
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

    public void addEvent(String name, String description, String location, String date, String time, boolean refundable, float fee) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        eventDAO.addEvent(name, description, location, date, time, refundable, fee, 0);

        System.out.println("Event created.");

    }

    public void editEvent(int code, String[] options, String[] edits) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();
        RequestDAO requestDAO = new RequestDAO();

        for (String option : options) {
            switch (option) {
                case "1" -> eventDAO.updateName(code, edits[0]);
                case "2" -> eventDAO.updateLocation(code, edits[1]);
                case "3" -> eventDAO.updateDate(code, edits[2]);
                case "4" -> eventDAO.updateTime(code, edits[3]);
                case "5" -> {
                    if (!eventDAO.getParticipants(code).isEmpty())
                        eventDAO.updateFee(code, Float.parseFloat(edits[4]));
                    else {
                        System.out.println("Event has participants. Fee cannot be changed.");
                    }
                }
                case "6" -> {
                    boolean refundable = edits[5].equals("yes");
                    eventDAO.updateRefundable(code, refundable);
                }
            }
        }

        System.out.println("Event updated.");

    }

    public void removeEvent(int code) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        eventDAO.removeEvent(code);

        System.out.println("Event removed.");

        Event event = eventDAO.getEvent(code);

        if (!event.isRefundable() || eventDAO.getParticipants(code).isEmpty())
            return;

        ParticipationDAO participationDAO = new ParticipationDAO();

        ArrayList<Participation> participations = participationDAO.getParticipationsByEvent(code);

        // FIXME: with this implementation, the refund is done with the current user's payment method, not with the one used to pay for the event

        for (Participation participation : participations) {
            participation.getUser().getPaymentMethod().refund(event);
            participationDAO.removeParticipation(participation.getUser().getId(), code);
        }

    }

    public void viewParticipations() throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        ArrayList<Participation> participations = participationDAO.getAllParticipations();

        System.out.println("\n+-------+------------------------------+------+----------------+----------------+");
        System.out.println("| Event | Event Name                   | User | username       | Payment Method |");
        System.out.println("+-------+------------------------------+------+----------------+----------------+");
        for (Participation participation : participations)
            System.out.printf("| %-5d | %-28s | %-4d | %-14s | %-14s |\n",
                    participation.getEvent().getCode(),
                    participation.getEvent().getName(),
                    participation.getUser().getId(),
                    participation.getUser().getUsername(),
                    participation.getUser().getPaymentMethodType());
        System.out.println("+-------+------------------------------+------+----------------+----------------+");

    }
}