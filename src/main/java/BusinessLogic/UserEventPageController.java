package main.java.BusinessLogic;

import main.java.DomainModel.Event;
import main.java.DomainModel.Participation;
import main.java.DomainModel.User;
import main.java.ORM.EventDAO;
import main.java.ORM.ParticipationDAO;

import java.sql.SQLException;
import java.util.ArrayList;

public class UserEventPageController {

    User user;

    public UserEventPageController(User user) { this.user = user; }

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

    public void searchAnEvent(String searchBy, String[] data) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        ArrayList<Event> events = new ArrayList<Event>();

        if (searchBy.equals("code")) {
            events.add(eventDAO.getEvent(Integer.parseInt(data[0])));
        } else if (searchBy.equals("name")) {
            events = eventDAO.getEventsByName(data[1]);
        } else if (searchBy.equals("location")) {
            events = eventDAO.getEventsByLocation(data[2]);
        } else if (searchBy.equals("date")) {
            events = eventDAO.getEventsByDate(data[3]);
        } else {
            System.out.println("Invalid search option.");
            return;
        }

        if (events.isEmpty()) {
            System.out.println("No events found.");
            return;
        }

        System.out.println("\n+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        System.out.println("| Code | Name                                               | Description                                        | Location                                     | Date                | Time                | Refundable | Fee     | Created By   |");
        System.out.println("+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        for (Event event : events)
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

    public void attendAnEvent(int code) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();
        ParticipationDAO participationDAO = new ParticipationDAO();

        if (eventDAO.getEvent(code) == null) {
            System.out.println("Event not found.");
            return;
        }

        if (participationDAO.getParticipation(user.getId(), code) != null) {
            System.out.println("You have already attended the event.");
            return;
        }

        if (eventDAO.getEvent(code).getCreatedBy() == user.getId()) {
            System.out.println("You cannot attend an event you created.");
            return;
        }

        user.getPaymentMethod().pay(eventDAO.getEvent(code));

        participationDAO.addParticipation(user.getId(), code, user.getPaymentMethodType());

        System.out.println("You have successfully attended the event.");

    }

    public void viewAttendedEvents() throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        System.out.println("\nEvents Attended:");

        System.out.println("\n+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        System.out.println("| Code | Name                                               | Description                                        | Location                                     | Date                | Time                | Refundable | Fee     | Created By   |");
        System.out.println("+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");
        for (Participation participation : participationDAO.getParticipationsByUser(user.getId()))
            System.out.printf("| %-4s | %-50s | %-50s | %-44s | %-19s | %-19s | %-10s | %-7s | %-12s |\n",
                    participation.getEvent().getCode(),
                    participation.getEvent().getName(),
                    participation.getEvent().getDescription(),
                    participation.getEvent().getLocation(),
                    participation.getEvent().getDate(),
                    participation.getEvent().getTime(),
                    participation.getEvent().isRefundable() ? "Yes" : "No",
                    Float.toString(participation.getEvent().getFee()),
                    Integer.toString(participation.getEvent().getCreatedBy()));
        System.out.println("+------+----------------------------------------------------+----------------------------------------------------+----------------------------------------------+---------------------+---------------------+------------+---------+--------------+");

    }

    public void removeParticipation(int code) throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        Event event = participationDAO.getParticipation(user.getId(), code).getEvent();

        if (event == null) {
            System.out.println("You have not attended the event.");
            return;
        }

        participationDAO.removeParticipation(user.getId(), code);

        System.out.println("You have successfully removed your participation.");

        // FIXME: with this implementation, the refund is done with the current user's payment method, not with the one used to pay for the event

        if (event.getFee() > 0 && event.isRefundable()) {
            user.getPaymentMethod().refund(event);
            System.out.println("You have been refunded.");
        }

    }

}