package main.java.BusinessLogic;

import main.java.DomainModel.Event;
import main.java.DomainModel.Participation;
import main.java.DomainModel.Request;
import main.java.DomainModel.User;
import main.java.ORM.EventDAO;
import main.java.ORM.ParticipationDAO;
import main.java.ORM.RequestDAO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class AdminEventController {

    public void seeRequests() throws SQLException, ClassNotFoundException {

        // FIXME: table visualization

        RequestDAO requestDAO = new RequestDAO();

        ArrayList<Request> requests = requestDAO.getAllRequests();

        System.out.println("\n+---------+----------------------------------------------------------------------------------------------------+---------------------+");
        System.out.println("| User ID | Description                                                                                        | Created At          |");
        System.out.println("+---------+----------------------------------------------------------------------------------------------------+---------------------+");
        for (Request request : requests) {
            System.out.printf("| %-7s | %-100s | %-19s |\n",
                    request.getUserId(),
                    request.getDescription(),
                    request.getCreated_at());
        }
        System.out.println("+---------+----------------------------------------------------------------------------------------------------+---------------------+");

    }

    public void removeRequest() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        RequestDAO requestDAO = new RequestDAO();

        int code;
        do {
            System.out.println("\nUser ID: ");
            code = scanner.nextInt();
        } while (code < 0);

        requestDAO.removeRequest(code);

        System.out.println("Request removed.");

    }

    public void removeAllRequests() throws SQLException, ClassNotFoundException {

        RequestDAO requestDAO = new RequestDAO();

        requestDAO.removeAllRequests();

        System.out.println("All requests removed.");

    }

    public void viewEvents() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
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

    public void addEvent() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        EventDAO eventDAO = new EventDAO();

        String name;
        do {
            System.out.println("\nEvent Name: ");
            name = scanner.nextLine();
        } while (name == null || name.isEmpty() || name.length() > 50);

        System.out.println("Event Description: ");
        String description = scanner.nextLine();

        String location;
        do {
            System.out.println("Event Location: ");
            location = scanner.nextLine();
        } while (location == null || location.isEmpty() || location.length() > 50);

        String date;
        do {
            System.out.println("Event Date: ");
            date = scanner.nextLine();
        } while (date == null || date.isEmpty() || date.length() > 50);

        String time;
        do {
            System.out.println("Event Time: ");
            time = scanner.nextLine();
        } while (time == null || time.isEmpty() || time.length() > 50);

        float fee;
        do {
            System.out.println("Event Fee: ");
            fee = scanner.nextFloat();
            scanner.nextLine();
        } while (fee < 0);

        boolean refundable;
        String refundableString;
        do {
            System.out.println("Refundable? (yes/no)");
            refundableString = scanner.nextLine().toLowerCase();
        } while (refundableString.isEmpty() || (!refundableString.equals("yes") && !refundableString.equals("no")  && !refundableString.equals("Yes") && !refundableString.equals("No")));
        refundable = refundableString.equals("yes");

        eventDAO.addEvent(name, description, location, date, time, refundable, fee, 0);

        System.out.println("Event created.");

    }

    public void editEvent() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        EventDAO eventDAO = new EventDAO();
        RequestDAO requestDAO = new RequestDAO();

        int code;
        do {
            System.out.println("\nEvent Code: ");
            code = scanner.nextInt();
            scanner.nextLine();
        } while (code < 0);

        System.out.println("\nWhat do you want to change? (you can choose multiple options written in a space-separated list)");
        System.out.println(
                """
                
                 1. Name
                 2. Location
                 3. Date
                 4. Time
                 5. Fee
                 6. Refundable
                """
        );
        String[] options = scanner.nextLine().split(" ");

        for (String option : options) {
            switch (option) {
                case "1" -> {
                    String name;
                    do {
                        System.out.println("\nNew Name: ");
                        name = scanner.nextLine();
                    } while (name == null || name.isEmpty() || name.length() > 50);
                    eventDAO.updateName(code, name);
                }
                case "2" -> {
                    String location;
                    do {
                        System.out.println("New Location: ");
                        location = scanner.nextLine();
                    } while (location == null || location.isEmpty() || location.length() > 50);
                    eventDAO.updateLocation(code, location);
                }
                case "3" -> {
                    String date;
                    do {
                        System.out.println("New Date: ");
                        date = scanner.nextLine();
                    } while (date == null || date.isEmpty() || date.length() > 50);
                    eventDAO.updateDate(code, date);
                }
                case "4" -> {
                    String time;
                    do {
                        System.out.println("New Time: ");
                        time = scanner.nextLine();
                    } while (time == null || time.isEmpty() || time.length() > 50);
                    eventDAO.updateTime(code, time);
                }
                case "5" -> {
                    float fee;
                    do {
                        System.out.println("New Fee: ");
                        fee = scanner.nextFloat();
                    } while (fee < 0);
                    if (!eventDAO.getParticipants(code).isEmpty())
                        eventDAO.updateFee(code, fee);
                    else {
                        System.out.println("Event has participants. Fee cannot be changed.");
                    }
                }
                case "6" -> {
                    boolean refundable;
                    String refundableString;
                    do {
                        System.out.println("Refundable? (yes/no)");
                        refundableString = scanner.nextLine();
                    } while (refundableString == null || (!refundableString.equals("yes") && !refundableString.equals("no")));
                    refundable = refundableString.equals("yes");
                    eventDAO.updateRefundable(code, refundable);
                }
            }
        }

        System.out.println("Event updated.");

    }

    public void removeEvent() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        EventDAO eventDAO = new EventDAO();

        int code;
        do {
            System.out.println("\nEvent Code: ");
            code = scanner.nextInt();
        } while (code < 0);

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

}