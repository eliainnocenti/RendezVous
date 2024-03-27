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

    public ArrayList<Event> viewEvents() throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        return eventDAO.getAllEvents();

    }

    public ArrayList<Event> searchAnEvent(String searchBy, String data) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        ArrayList<Event> events = new ArrayList<Event>();

        if (searchBy.equals("code")) {
            events.add(eventDAO.getEvent(Integer.parseInt(data)));
        } else if (searchBy.equals("name")) {
            events = eventDAO.getEventsByName(data);
        } else if (searchBy.equals("location")) {
            events = eventDAO.getEventsByLocation(data);
        } else if (searchBy.equals("date")) {
            events = eventDAO.getEventsByDate(data);
        }

        return events;

    }

    public void attendAnEvent(int code) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();
        ParticipationDAO participationDAO = new ParticipationDAO();

        if (eventDAO.getEvent(code) == null) {
            System.out.println("Event not found.");
            return;
        }

        if (user.getPaymentMethod() == null) {
            System.out.println("You must set a payment method before attending an event.");
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

    public ArrayList<Event> viewAttendedEvents() throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        System.out.println("\nEvents Attended:");

        ArrayList<Event> events = new ArrayList<Event>();

        for (Participation participation : participationDAO.getParticipationsByUser(user.getId()))
            events.add(participation.getEvent());

        return events;

    }

    public void removeParticipation(int code) throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        Participation participation = participationDAO.getParticipation(user.getId(), code);

        if (participation == null) {
            System.out.println("You have not attended the event.");
            return;
        }

        Event event = participationDAO.getParticipation(user.getId(), code).getEvent();

        if (!event.isRefundable()) {
            System.out.println("The event is not refundable. Your participation will be removed anyway.");
        }

        participationDAO.removeParticipation(user.getId(), code);

        System.out.println("You have successfully removed your participation.");

        // FIXME: with this implementation, the refund is done with the current user's payment method, not with the one used to pay for the event

        // FIXME: in fact, if the user delete the payment method, the refund will not be possible (NullPointerException)

        if (event.getFee() > 0 && event.isRefundable()) {
            user.getPaymentMethod().refund(event);
            System.out.println("You have been refunded.");
        }

    }

}