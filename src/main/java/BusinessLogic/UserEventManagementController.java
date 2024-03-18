package main.java.BusinessLogic;

import main.java.DomainModel.User;
import main.java.DomainModel.Event;
import main.java.ORM.EventDAO;
import main.java.ORM.ParticipationDAO;
import main.java.ORM.RequestDAO;

import java.sql.SQLException;
import java.util.Scanner;

public class UserEventManagementController {

    User user;

    public UserEventManagementController(User user) { this.user = user; }

    public void createEvent() throws SQLException, ClassNotFoundException {

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
            refundableString = scanner.nextLine();
        } while (refundableString == null || (!refundableString.equals("yes") && !refundableString.equals("no")));
        refundable = refundableString.equals("yes");

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

    public void viewCreatedEventsAttendees() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        ParticipationDAO participationDAO = new ParticipationDAO();
        EventDAO eventDAO = new EventDAO();

        System.out.println("\nEvent Code: ");
        int code = scanner.nextInt();
        scanner.nextLine();

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

    public void updateAnEventDescription() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        EventDAO eventDAO = new EventDAO();

        System.out.println("\nEvent Code: ");
        int code = scanner.nextInt();
        scanner.nextLine();

        System.out.println("New Description: ");
        String description = scanner.nextLine();

        eventDAO.updateDescription(code, description);

        System.out.println("Description updated.");

    }

    public void requestToChangeAnEventAttributes() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        EventDAO eventDAO = new EventDAO();
        RequestDAO requestDAO = new RequestDAO();

        int code;
        do {
            System.out.println("\nEvent Code: ");
            code = scanner.nextInt();
            scanner.nextLine();
        } while (eventDAO.getEvent(code).getCreatedBy() != user.getId());

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

        StringBuilder request = new StringBuilder();
        request.append("| UPDATE | Event Code: ").append(code).append(" | ");

        for (String option : options) {
            switch (option) {
                case "1" -> {
                    String name;
                    do {
                        System.out.println("\nNew Name: ");
                        name = scanner.nextLine();
                    } while (name == null || name.isEmpty() || name.length() > 50);
                    request.append(" Name: ").append(name).append(" |");
                }
                case "2" -> {
                    String location;
                    do {
                        System.out.println("New Location: ");
                        location = scanner.nextLine();
                    } while (location == null || location.isEmpty() || location.length() > 50);
                    request.append(" Location: ").append(location).append(" |");
                }
                case "3" -> {
                    String date;
                    do {
                        System.out.println("New Date: ");
                        date = scanner.nextLine();
                    } while (date == null || date.isEmpty() || date.length() > 50);
                    request.append(" Date: ").append(date).append(" |");
                }
                case "4" -> {
                    String time;
                    do {
                        System.out.println("New Time: ");
                        time = scanner.nextLine();
                    } while (time == null || time.isEmpty() || time.length() > 50);
                    request.append(" Time: ").append(time).append(" |");
                }
                case "5" -> {
                    float fee;
                    do {
                        System.out.println("New Fee: ");
                        fee = scanner.nextFloat();
                        scanner.nextLine();
                    } while (fee < 0);
                    request.append(" Fee: ").append(Float.toString(fee)).append(" |");
                }
                case "6" -> {
                    boolean refundable;
                    String refundableString;
                    do {
                        System.out.println("Refundable? (yes/no)");
                        refundableString = scanner.nextLine().toLowerCase();
                    } while (refundableString.isEmpty() || (!refundableString.equals("yes") && !refundableString.equals("no") && !refundableString.equals("Yes") && !refundableString.equals("No")));
                    refundable = refundableString.equals("yes");
                    request.append(" Refundable: ").append(refundable ? "Yes" : "No").append(" |");
                }
            }
        }

        requestDAO.addRequest(user.getId(), request.toString());

        System.out.println("Request sent.");

    }

    public void requestToCancelAnEvent() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        EventDAO eventDAO = new EventDAO();
        RequestDAO requestDAO = new RequestDAO();

        int code;
        do {
            System.out.println("\nEvent Code: ");
            code = scanner.nextInt();
            scanner.nextLine();
        } while (eventDAO.getEvent(code).getCreatedBy() != user.getId());

        String request = "| CANCEL | Event Code: " + code + " |";

        requestDAO.addRequest(user.getId(), request);

        System.out.println("Request sent.");

    }

}