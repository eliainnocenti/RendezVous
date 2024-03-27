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

    public ArrayList<Request> seeRequests() throws SQLException, ClassNotFoundException {

        RequestDAO requestDAO = new RequestDAO();

        return requestDAO.getAllRequests();

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

    public ArrayList<Event> viewEvents() throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        return eventDAO.getAllEvents();

    }

    public void addEvent(String name, String description, String location, String date, String time, boolean refundable, float fee) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

        eventDAO.addEvent(name, description, location, date, time, refundable, fee, 0);

        System.out.println("Event created.");

    }

    public void editEvent(int code, String[] options, String[] edits) throws SQLException, ClassNotFoundException {

        EventDAO eventDAO = new EventDAO();

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

    public ArrayList<Participation> viewParticipations() throws SQLException, ClassNotFoundException {

        ParticipationDAO participationDAO = new ParticipationDAO();

        return participationDAO.getAllParticipations();

    }
}