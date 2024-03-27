package main.java;

import main.java.BusinessLogic.*;
import main.java.DomainModel.*;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        handleLoginAction();

    }

    public static void handleLoginAction() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        LoginController loginController = new LoginController();

        String input;

        do {

            System.out.println(
                    """
                     
                     RENDEZVOUS
                     1. Sign in
                     2. Sign up
                     3. Admin
                     4. Exit
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    Scanner scanner1 = new Scanner(System.in);

                    System.out.println("\nUsername: ");
                    String username = scanner1.nextLine();
                    System.out.println("Password: ");
                    String password = scanner1.nextLine();

                    User user = loginController.login(username, password);

                    if (user != null)
                        handleUserAction(user);

                }
                case "2" -> {

                    String[] data = register();

                    User user = loginController.register(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], data[8], data[9]);

                    if (user != null)
                        handleUserAction(user);
                }
                case "3" -> {

                    Scanner scanner3 = new Scanner(System.in);

                    System.out.println("\nPassword Admin: ");
                    String password = scanner3.nextLine();

                    if (loginController.adminLogin(password) != null)
                        handleAdminAction();
                }
                case "4" -> System.exit(0);
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleUserAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     USER PAGE
                     1. Event page: view, search and attend events
                     2. Create and manage your own events
                     3. User profile: view and edit your profile
                     4. Log out
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> handleEventPageAction(user);
                case "2" -> handleEventManagementAction(user);
                case "3" -> handleUserProfileAction(user);
                case "4" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleEventPageAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserEventPageController userEventPageController = new UserEventPageController(user);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     EVENT PAGE
                     1. View events
                     2. Search an event
                     3. Attend an event
                     4. View the events you are attending
                     5. Unattend an event
                     6. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    ArrayList<Event> events = userEventPageController.viewEvents();

                    if (events.isEmpty()) {
                        System.out.println("No events found.");
                        break;
                    }

                    printEvents(events);

                }
                case "2" -> {

                    Scanner scanner2 = new Scanner(System.in);

                    System.out.println("\nHow would you like to search for an event? (code, name, location, date)"); // FIXME: add more options
                    String searchBy = scanner2.nextLine();

                    String data;

                    if (searchBy.equals("code")) {
                        System.out.println("\nEvent Code: ");
                        int code = scanner2.nextInt();
                        data = Integer.toString(code);
                    } else if (searchBy.equals("name")) {
                        System.out.println("\nEvent Name: ");
                        data = scanner2.nextLine();
                    } else if (searchBy.equals("location")) {
                        System.out.println("\nEvent Location: ");
                        data = scanner2.nextLine();
                    } else if (searchBy.equals("date")) {
                        System.out.println("\nEvent Date: ");
                        data = scanner2.nextLine();
                    } else {
                        System.out.println("Invalid search option.");
                        break;
                    }

                    ArrayList<Event> events = userEventPageController.searchAnEvent(searchBy, data);

                    if (events.isEmpty()) {
                        System.out.println("No events found.");
                        break;
                    }

                    printEvents(events);

                }
                case "3" -> {

                    Scanner scanner3 = new Scanner(System.in);

                    System.out.println("\nEvent Code: ");
                    int code = scanner3.nextInt();
                    scanner3.nextLine();

                    userEventPageController.attendAnEvent(code);

                }
                case "4" -> {

                    ArrayList<Event> events = userEventPageController.viewAttendedEvents();

                    if (events.isEmpty()) {
                        System.out.println("No events found.");
                        break;
                    }

                    System.out.println("\nEvents Attended:");

                    printEvents(events);

                }
                case "5" -> {

                    Scanner scanner5 = new Scanner(System.in);

                    System.out.println("\nEvent Code: ");
                    int code = scanner5.nextInt();
                    scanner5.nextLine();

                    userEventPageController.removeParticipation(code);

                }
                case "6" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleEventManagementAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserEventManagementController userEventManagementController = new UserEventManagementController(user);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     EVENT MANAGEMENT
                     1. Create an event
                     2. View your events
                     3. View who's attending your events
                     4. Edit your events
                     5. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    Scanner scanner1 = new Scanner(System.in);

                    String name;
                    do {
                        System.out.println("\nEvent Name: ");
                        name = scanner1.nextLine();
                    } while (name == null || name.isEmpty() || name.length() > 50);

                    System.out.println("Event Description: ");
                    String description = scanner1.nextLine();

                    String location;
                    do {
                        System.out.println("Event Location: ");
                        location = scanner1.nextLine();
                    } while (location == null || location.isEmpty() || location.length() > 50);

                    String date;
                    do {
                        System.out.println("Event Date: ");
                        date = scanner1.nextLine();
                    } while (date == null || date.isEmpty() || date.length() > 50);

                    String time;
                    do {
                        System.out.println("Event Time: ");
                        time = scanner1.nextLine();
                    } while (time == null || time.isEmpty() || time.length() > 50);

                    float fee;
                    do {
                        System.out.println("Event Fee: ");
                        fee = scanner1.nextFloat();
                        scanner1.nextLine();
                    } while (fee < 0);

                    boolean refundable;
                    String refundableString;
                    do {
                        System.out.println("Refundable? (yes/no)");
                        refundableString = scanner1.nextLine();
                    } while (refundableString == null || (!refundableString.equals("yes") && !refundableString.equals("no")));
                    refundable = refundableString.equals("yes");

                    userEventManagementController.createEvent(name, description, location, date, time, refundable, fee);

                }
                case "2" -> {

                    ArrayList<Event> events = userEventManagementController.viewCreatedEvents();

                    System.out.println("\nCreated events:");

                    printEvents(events);

                }
                case "3" -> {

                    Scanner scanner3 = new Scanner(System.in);

                    System.out.println("\nEvent Code: ");
                    int code = scanner3.nextInt();
                    scanner3.nextLine();

                    ArrayList<User> users = userEventManagementController.viewCreatedEventsAttendees(code);

                    if (users == null || users.isEmpty()) {
                        System.out.println("No attendees found.");
                        break;
                    }

                    System.out.println("\nAttendees:");

                    printUsers1(users);

                }
                case "4" -> handleEventEditorAction(user);
                case "5" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleEventEditorAction(User user) throws SQLException, ClassNotFoundException {

            Scanner scanner = new Scanner(System.in);
            UserEventManagementController userEventManagementController = new UserEventManagementController(user);

            String input;

            label:
            do {

                System.out.println(
                        """
                        
                        EVENT EDITOR
                        (only the admin can edit the event's date, location, and price once the event is created)
                        1. Change an event's description
                        2. Request to change an event's date, location and price
                        3. Request to cancel an event
                        4. Go back
                        """
                );

                input = scanner.nextLine();

                switch (input) {
                    case "1" -> {

                        Scanner scanner1 = new Scanner(System.in);

                        System.out.println("\nEvent Code: ");
                        int code = scanner1.nextInt();
                        scanner1.nextLine();

                        System.out.println("New Description: ");
                        String description = scanner1.nextLine();

                        userEventManagementController.updateAnEventDescription(code, description);

                    }
                    case "2" -> {

                        Scanner scanner2 = new Scanner(System.in);

                        int code;
                        do {
                            System.out.println("\nEvent Code: ");
                            code = scanner2.nextInt();
                            scanner2.nextLine();
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
                        String[] options = scanner2.nextLine().split(" ");

                        StringBuilder request = new StringBuilder();
                        request.append("| UPDATE | Event Code: ").append(code).append(" | ");

                        for (String option : options) {
                            switch (option) {
                                case "1" -> {
                                    String name;
                                    do {
                                        System.out.println("\nNew Name: ");
                                        name = scanner2.nextLine();
                                    } while (name == null || name.isEmpty() || name.length() > 50);
                                    request.append(" Name: ").append(name).append(" |");
                                }
                                case "2" -> {
                                    String location;
                                    do {
                                        System.out.println("New Location: ");
                                        location = scanner2.nextLine();
                                    } while (location == null || location.isEmpty() || location.length() > 50);
                                    request.append(" Location: ").append(location).append(" |");
                                }
                                case "3" -> {
                                    String date;
                                    do {
                                        System.out.println("New Date: ");
                                        date = scanner2.nextLine();
                                    } while (date == null || date.isEmpty() || date.length() > 50);
                                    request.append(" Date: ").append(date).append(" |");
                                }
                                case "4" -> {
                                    String time;
                                    do {
                                        System.out.println("New Time: ");
                                        time = scanner2.nextLine();
                                    } while (time == null || time.isEmpty() || time.length() > 50);
                                    request.append(" Time: ").append(time).append(" |");
                                }
                                case "5" -> {
                                    float fee;
                                    do {
                                        System.out.println("New Fee: ");
                                        fee = scanner2.nextFloat();
                                        scanner2.nextLine();
                                    } while (fee < 0);
                                    request.append(" Fee: ").append(Float.toString(fee)).append(" |");
                                }
                                case "6" -> {
                                    boolean refundable;
                                    String refundableString;
                                    do {
                                        System.out.println("Refundable? (yes/no)");
                                        refundableString = scanner2.nextLine().toLowerCase();
                                    } while (refundableString.isEmpty() || (!refundableString.equals("yes") && !refundableString.equals("no") && !refundableString.equals("Yes") && !refundableString.equals("No")));
                                    refundable = refundableString.equals("yes");
                                    request.append(" Refundable: ").append(refundable ? "Yes" : "No").append(" |");
                                }
                            }
                        }

                        userEventManagementController.requestToChangeAnEventAttributes(code, request.toString());

                    }
                    case "3" -> {

                        Scanner scanner3 = new Scanner(System.in);

                        int code;
                        do {
                            System.out.println("\nEvent Code: ");
                            code = scanner3.nextInt();
                            scanner3.nextLine();
                        } while (code < 0);

                        userEventManagementController.requestToCancelAnEvent(code);

                    }
                    case "4" -> { break label; }
                    default -> System.out.println("Invalid input. Please try again.");
                }

            } while (true);
    }

    public static void handleUserProfileAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     USER PROFILE
                     1. View your profile
                     2. Edit your profile
                     3. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> viewProfile(user);
                case "2" -> handleProfileEditorAction(user);
                case "3" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleProfileEditorAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     PROFILE EDITOR
                     1. Personal data
                     2. Login data
                     3. Payment data
                     4. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> handleProfileEditorPersonalDataAction(user);
                case "2" -> handleProfileEditorLoginDataAction(user);
                case "3" -> handleProfileEditorPaymentDataAction(user);
                case "4" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleProfileEditorPersonalDataAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserProfileController userProfileController = new UserProfileController(user);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     PERSONAL DATA
                     1. Update name
                     2. Update surname
                     3. Update age
                     4. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    Scanner scanner1 = new Scanner(System.in);

                    System.out.println("\nNew Name: ");
                    String newName = scanner1.nextLine();

                    userProfileController.updateName(newName);

                }
                case "2" -> {

                    Scanner scanner2 = new Scanner(System.in);

                    System.out.println("\nNew Surname: ");
                    String newSurname = scanner2.nextLine();

                    userProfileController.updateSurname(newSurname);

                }
                case "3" -> {

                    Scanner scanner3 = new Scanner(System.in);

                    System.out.println("\nNew Age: ");
                    int newAge = scanner3.nextInt();

                    userProfileController.updateAge(newAge);

                }
                case "4" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleProfileEditorLoginDataAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserProfileController userProfileController = new UserProfileController(user);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     LOGIN DATA
                     1. Update username
                     2. Update email
                     3. Update password
                     4. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    Scanner scanner1 = new Scanner(System.in);

                    ArrayList<String> usernames = userProfileController.getAllUsernames();

                    String newUsername;

                    do {

                        System.out.println("\nNew Username: ");
                        newUsername = scanner1.nextLine();

                        if (newUsername == null || newUsername.isEmpty()) {
                            System.out.println("Username cannot be null or empty.");
                            continue;
                        }

                        if (usernames.contains(newUsername)) {
                            System.out.println("Username already exists.");
                        }

                    } while (usernames.contains(newUsername));

                    userProfileController.updateUsername(newUsername);

                }
                case "2" -> {

                    Scanner scanner2 = new Scanner(System.in);

                    ArrayList<String> emails = userProfileController.getAllEmails();

                    String newEmail;

                    System.out.println();

                    do {

                        System.out.println("New Email: ");
                        newEmail = scanner2.nextLine();

                        if (newEmail == null || newEmail.isEmpty()) {
                            System.out.println("Email cannot be null or empty.");
                            continue;
                        }

                        if (emails.contains(newEmail)) {
                            System.out.println("Email already exists.");
                        }

                    } while (emails.contains(newEmail));

                    userProfileController.updateEmail(newEmail);

                }
                case "3" -> {

                    Scanner scanner3 = new Scanner(System.in);

                    String newPassword;

                    System.out.println();

                    do {

                        System.out.println("New Password: ");
                        newPassword = scanner3.nextLine();

                        if (newPassword == null || newPassword.isEmpty()) {
                            System.out.println("Password cannot be null or empty.");
                        }

                    } while (newPassword == null || newPassword.isEmpty());

                    userProfileController.updatePassword(newPassword);

                }
                case "4" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleProfileEditorPaymentDataAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserProfileController userProfileController = new UserProfileController(user);

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     PAYMENT DATA
                     1. Set a payment method
                     2. Change the payment method
                     3. Remove the payment method
                     4. Update the credit card
                     5. Update the PayPal account
                     6. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    Scanner scanner1 = new Scanner(System.in);

                    if (user.getPaymentMethodType() != null) {
                        System.out.println("\nYou already have a payment method. Do you want to update it? (yes/no)");
                        String answer = scanner1.nextLine();
                        if (!answer.equals("yes")) { return; } // TODO: check if it's correct
                        userProfileController.removePaymentMethod();
                    }

                    String[] data = setPaymentMethod();

                    userProfileController.setPaymentMethod(data[0], data[1], data[2], data[3]);

                }
                case "2" -> {

                    userProfileController.removePaymentMethod();

                    String[] data = setPaymentMethod();

                    userProfileController.setPaymentMethod(data[0], data[1], data[2], data[3]);

                }
                case "3" -> userProfileController.removePaymentMethod();
                case "4" -> { // FIXME: is it redundant?

                    userProfileController.removePaymentMethod();

                    Scanner scanner4 = new Scanner(System.in);

                    System.out.println();
                    System.out.println("Card Number: ");
                    String cardNumber = scanner4.nextLine();
                    System.out.println("Card Expiration Date: ");
                    String cardExpirationDate = scanner4.nextLine();
                    System.out.println("Card Security Code: ");
                    String cardSecurityCode = scanner4.nextLine();

                    userProfileController.updateCreditCard(cardNumber, cardExpirationDate, cardSecurityCode);

                }
                case "5" -> { // FIXME: is it redundant?

                    userProfileController.removePaymentMethod();

                    Scanner scanner5 = new Scanner(System.in);

                    System.out.println();
                    System.out.println("Email: ");
                    String accountEmail = scanner5.nextLine();
                    System.out.println("Password: ");
                    String accountPassword = scanner5.nextLine();

                    userProfileController.updatePayPal(accountEmail, accountPassword);

                }
                case "6" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleAdminAction() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        String input;

        label:
        do {

            System.out.println(
                    """
               
                     ADMIN
                     1. Users management
                     2. Events management
                     3. Extra
                     4. Log out
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> handleAdminUsersAction();
                case "2" -> handleAdminEventsAction();
                case "3" -> handleAdminExtraAction();
                case "4" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleAdminUsersAction() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        AdminUserController adminUserController = new AdminUserController();

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     USERS MANAGEMENT
                     1. View users
                     2. Search user
                     3. Add user
                     4. Remove user
                     5. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    ArrayList<User> users = adminUserController.viewUsers();

                    if (users.isEmpty()) {
                        System.out.println("No users found.");
                        break;
                    }

                    printUsers2(users);

                }
                case "2" -> {

                    Scanner scanner2 = new Scanner(System.in);

                    System.out.println("\nUsername: ");
                    String username = scanner2.nextLine();

                    ArrayList<User> users = adminUserController.searchUser(username);

                    if (users == null || users.isEmpty()) {
                        System.out.println("No users found.");
                        break;
                    }

                    printUsers2(users);

                }
                case "3" -> {

                    String[] data = register();

                    adminUserController.addUser(data[0], data[1], Integer.parseInt(data[2]), data[3], data[4], data[5], data[6], data[7], data[8], data[9]);

                }
                case "4" -> {

                    Scanner scanner4 = new Scanner(System.in);

                    System.out.println("\nUsername: ");
                    String username = scanner4.nextLine();

                    adminUserController.removeUser(username);

                }
                case "5" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleAdminEventsAction() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        AdminEventController adminEventController = new AdminEventController();

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     EVENTS MANAGEMENT
                     1. See requests
                     2. Remove a request
                     3. Remove all requests
                     4. View events
                     5. Create an event
                     6. Edit an event
                     7. Cancel an event
                     8. View all the participations
                     9. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    ArrayList<Request> requests = adminEventController.seeRequests();

                    if (requests.isEmpty()) {
                        System.out.println("No requests found.");
                        break;
                    }

                    printRequests(requests);

                }
                case "2" -> {

                    Scanner scanner2 = new Scanner(System.in);

                    int code;
                    do {
                        System.out.println("\nUser ID: ");
                        code = scanner2.nextInt();
                    } while (code < 0);

                    adminEventController.removeRequest(code);

                }
                case "3" -> adminEventController.removeAllRequests();
                case "4" -> {

                    ArrayList<Event> events = adminEventController.viewEvents();

                    if (events.isEmpty()) {
                        System.out.println("No events found.");
                        break;
                    }

                    printEvents(events);

                }
                case "5" -> {

                    Scanner scanner5 = new Scanner(System.in);

                    String name;
                    do {
                        System.out.println("\nEvent Name: ");
                        name = scanner5.nextLine();
                    } while (name == null || name.isEmpty() || name.length() > 50);

                    System.out.println("Event Description: ");
                    String description = scanner5.nextLine();

                    String location;
                    do {
                        System.out.println("Event Location: ");
                        location = scanner5.nextLine();
                    } while (location == null || location.isEmpty() || location.length() > 50);

                    String date;
                    do {
                        System.out.println("Event Date: ");
                        date = scanner5.nextLine();
                    } while (date == null || date.isEmpty() || date.length() > 50);

                    String time;
                    do {
                        System.out.println("Event Time: ");
                        time = scanner5.nextLine();
                    } while (time == null || time.isEmpty() || time.length() > 50);

                    float fee;
                    do {
                        System.out.println("Event Fee: ");
                        fee = scanner5.nextFloat();
                        scanner5.nextLine();
                    } while (fee < 0);

                    boolean refundable;
                    String refundableString;
                    do {
                        System.out.println("Refundable? (yes/no)");
                        refundableString = scanner5.nextLine().toLowerCase();
                    } while (refundableString.isEmpty() || (!refundableString.equals("yes") && !refundableString.equals("no")  && !refundableString.equals("Yes") && !refundableString.equals("No")));
                    refundable = refundableString.equals("yes");

                    adminEventController.addEvent(name, description, location, date, time, refundable, fee);

                }
                case "6" -> {

                    Scanner scanner6 = new Scanner(System.in);

                    int code;
                    do {
                        System.out.println("\nEvent Code: ");
                        code = scanner6.nextInt();
                        scanner6.nextLine();
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
                    String[] options = scanner6.nextLine().split(" ");

                    String[] data = new String[6];

                    for (String option : options) {
                        switch (option) {
                            case "1" -> {
                                String name;
                                do {
                                    System.out.println("\nNew Name: ");
                                    name = scanner6.nextLine();
                                } while (name == null || name.isEmpty() || name.length() > 50);
                                data[0] = name;
                            }
                            case "2" -> {
                                String location;
                                do {
                                    System.out.println("New Location: ");
                                    location = scanner6.nextLine();
                                } while (location == null || location.isEmpty() || location.length() > 50);
                                data[1] = location;
                            }
                            case "3" -> {
                                String date;
                                do {
                                    System.out.println("New Date: ");
                                    date = scanner6.nextLine();
                                } while (date == null || date.isEmpty() || date.length() > 50);
                                data[2] = date;
                            }
                            case "4" -> {
                                String time;
                                do {
                                    System.out.println("New Time: ");
                                    time = scanner6.nextLine();
                                } while (time == null || time.isEmpty() || time.length() > 50);
                                data[3] = time;
                            }
                            case "5" -> {
                                float fee;
                                do {
                                    System.out.println("New Fee: ");
                                    fee = scanner6.nextFloat();
                                } while (fee < 0);
                                data[4] = Float.toString(fee);
                            }
                            case "6" -> {
                                String refundableString;
                                do {
                                    System.out.println("Refundable? (yes/no)");
                                    refundableString = scanner6.nextLine();
                                } while (refundableString == null || (!refundableString.equals("yes") && !refundableString.equals("no")));
                                data[5] = refundableString;
                            }
                        }
                    }

                    adminEventController.editEvent(code, options, data);

                }
                case "7" -> {

                    Scanner scanner7 = new Scanner(System.in);

                    int code;
                    do {
                        System.out.println("\nEvent Code: ");
                        code = scanner7.nextInt();
                    } while (code < 0);

                    adminEventController.removeEvent(code);

                }
                case "8" -> {

                    ArrayList<Participation> participations = adminEventController.viewParticipations();

                    if (participations.isEmpty()) {
                        System.out.println("No participations found.");
                        break;
                    }

                    printParticipations(participations);

                }
                case "9" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleAdminExtraAction() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        AdminExtraController adminExtraController = new AdminExtraController();

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     EXTRA
                     1. Reset password
                     2. Reset database
                     3. Generate a default database
                     4. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> {

                    Scanner scanner1 = new Scanner(System.in);

                    System.out.println("\nNew admin password: ");
                    String newPassword;
                    do {
                        newPassword = scanner1.nextLine();
                    } while (newPassword.isEmpty());

                    System.out.println("Confirm new admin password: ");
                    String confirmPassword;

                    do {
                        confirmPassword = scanner1.nextLine();
                    } while (!newPassword.equals(confirmPassword));

                    adminExtraController.resetPassword(newPassword);

                }
                case "2" -> adminExtraController.resetDatabase();
                case "3" -> adminExtraController.generateDefaultDatabase();
                case "4" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    private static String[] register() {

        Scanner scanner2 = new Scanner(System.in);

        // personal data
        System.out.println("\nWelcome! Please provide the following information to register:");
        System.out.println("Name: ");
        String name = scanner2.nextLine();
        System.out.println("Surname: ");
        String surname = scanner2.nextLine();
        System.out.println("Age: ");
        int age;
        String ageString = scanner2.nextLine();
        if (ageString.isEmpty()) {
            age = 0;
        } else {
            age = Integer.parseInt(ageString);
        }

        // login data
        String username, email, password;
        do {
            System.out.println("\u001B[3m" + "Username, Email and Password are required fields." + "\u001B[0m");
            System.out.println("Username: ");
            username = scanner2.nextLine();
            System.out.println("Email: ");
            email = scanner2.nextLine();
            System.out.println("Password: ");
            password = scanner2.nextLine();
        } while (username == null || username.isEmpty() || email == null || email.isEmpty() || password == null || password.isEmpty());

        // payment data
        System.out.println("Payment Method: ");
        String paymentMethod = scanner2.nextLine();
        String cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword;
        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("creditcard") || paymentMethod.equals("credit card")) {
            System.out.println("Card Number: ");
            cardNumberORuniqueCode = scanner2.nextLine();
            System.out.println("Card Expiration Date: ");
            cardExpirationDateORaccountEmail = scanner2.nextLine();
            System.out.println("Card Security Code: ");
            cardSecurityCodeORaccountPassword = scanner2.nextLine();
        } else if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
            cardNumberORuniqueCode = null;
            System.out.println("Email: ");
            cardExpirationDateORaccountEmail = scanner2.nextLine();
            System.out.println("Password: ");
            cardSecurityCodeORaccountPassword = scanner2.nextLine();
        } else {
            cardNumberORuniqueCode = null;
            cardExpirationDateORaccountEmail = null;
            cardSecurityCodeORaccountPassword = null;
        }

        return new String[] {name, surname, Integer.toString(age), username, email, password, paymentMethod, cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword};

    }

    private static String[] setPaymentMethod() {

        Scanner scanner1 = new Scanner(System.in);

        System.out.println("\nPayment Method: ");
        String paymentMethod = scanner1.nextLine();

        String cardNumberORuniqueCode = null;
        String cardExpirationDateORaccountEmail = null;
        String cardSecurityCodeORaccountPassword = null;

        if (paymentMethod.equals("CreditCard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("creditcard") || paymentMethod.equals("credit card")) {
            paymentMethod = "CreditCard";
            System.out.println();
            System.out.println("Card Number: ");
            cardNumberORuniqueCode = scanner1.nextLine();
            System.out.println("Card Expiration Date: ");
            cardExpirationDateORaccountEmail = scanner1.nextLine();
            System.out.println("Card Security Code: ");
            cardSecurityCodeORaccountPassword = scanner1.nextLine();
        } else if (paymentMethod.equals("PayPal") || paymentMethod.equals("paypal")) {
            paymentMethod = "PayPal";
            System.out.println();
            System.out.println("Email: ");
            cardExpirationDateORaccountEmail = scanner1.nextLine();
            System.out.println("Password: ");
            cardSecurityCodeORaccountPassword = scanner1.nextLine();
        } else {
            System.out.println("Invalid payment method.");
        }

        return new String[] {paymentMethod, cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword};

    }

    private static void printEvents(ArrayList<Event> events) {

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

    private static void printUsers1(ArrayList<User> users) {

        System.out.println("\n+------+-----------------+-----------------+-------------------------------------+-----------------+-----------------+");
        System.out.println("| ID   | Name            | Surname         | Email                               | Username        | Payment Method  |");
        System.out.println("+------+-----------------+-----------------+-------------------------------------+-----------------+-----------------+");
        for (User user : users)
            System.out.printf("| %-4s | %-15s | %-15s | %-35s | %-15s | %-15s |\n",
                    user.getId(),
                    user.getName(),
                    user.getSurname(),
                    user.getEmail(),
                    user.getUsername(),
                    user.getPaymentMethodType());
        System.out.println("+------+-----------------+-----------------+-------------------------------------+-----------------+-----------------+");

    }

    private static void printUsers2(ArrayList<User> users) {

        System.out.println("\n+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");
        System.out.println("| ID | Name       | Surname    | Age | Username         | Email                            | Password           | CreditCard   | PayPal |");
        System.out.println("+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");
        for (User user : users) {
            System.out.printf("| %-3s| %-11s| %-11s| %-4s| %-17s| %-33s| %-19s| %-13s| %-7s|\n",
                    (!Objects.equals(Integer.toString(user.getId()), "null") && !Objects.equals(Integer.toString(user.getId()), null)) ? user.getId() : "",
                    (!Objects.equals(user.getName(), "null") && !Objects.equals(user.getName(), null)) ? user.getName() : "",
                    (!Objects.equals(user.getSurname(), "null") && !Objects.equals(user.getSurname(), null)) ? user.getSurname() : "",
                    (!Objects.equals(user.getAge(), 0)) ? Integer.toString(user.getAge()) : "",
                    user.getUsername(),
                    user.getEmail(),
                    user.getPassword(),
                    (!Objects.equals(user.getPaymentCode()[0], "null")) ? user.getPaymentCode()[0] : "",
                    (!Objects.equals(user.getPaymentCode()[1], "null")) ? user.getPaymentCode()[1] : "");
        }
        System.out.println("+----+------------+------------+-----+------------------+----------------------------------+--------------------+--------------+--------+");

    }

    private static void viewProfile(User user) {

        System.out.println("\nName: " + user.getName());
        System.out.println("Surname: " + user.getSurname());
        System.out.println("Age: " + user.getAge());
        System.out.println("Username: " + user.getUsername());
        System.out.println("Email: " + user.getEmail());
        System.out.println("Password: " + user.getPassword());
        if (user.getPaymentMethodType() != null) {
            System.out.println("Payment Method: " + user.getPaymentMethodType());
            System.out.println("Payment Data: \n" + user.getPaymentData());
        }

    }

    private static void printRequests(ArrayList<Request> requests) {

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

    private static void printParticipations(ArrayList<Participation> participations) {

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