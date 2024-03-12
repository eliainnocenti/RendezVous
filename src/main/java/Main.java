package main.java;

import main.java.BusinessLogic.*;
import main.java.DomainModel.*;

import java.sql.SQLException;
import java.util.Scanner;

// FIXME: the password attribute must be contained into "" (?) because it's important if there's capital letters.

// TODO: check all the strings/attributes -> decide (if it is the case) if they must be null or empty strings

// FIXME: think what is the best way to handle the exceptions
/*

    try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
        }

    oppure

    try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("");
        } catch (SQLException e) {
            System.err.println("" + e.getMessage());
        }

 */

// FIXME: check all the .close() methods

// FIXME: encapsulate methods in UserDAO using CreditCardDAO and PayPalDAO (check if there are more)

// TODO: put messages in the try-catch blocks

// TODO: put comments in sql files

// TODO: general check of the code and general refactor

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
                    User user = loginController.login();
                    if (user != null)
                        handleUserAction(user);
                }
                case "2" -> {
                    User user = loginController.register();
                    if (user != null)
                        handleUserAction(user);
                }
                case "3" -> {
                    if (loginController.adminLogin() != null)
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
                     5. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> userEventPageController.viewEvents();
                case "2" -> userEventPageController.searchAnEvent();
                case "3" -> userEventPageController.attendAnEvent();
                case "4" -> userEventPageController.viewAttendedEvents();
                case "5" -> { break label; }
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
                case "1" -> userEventManagementController.createEvent();
                case "2" -> userEventManagementController.viewCreatedEvents();
                case "3" -> userEventManagementController.viewCreatedEventsAttendees();
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
                    case "1" -> userEventManagementController.updateAnEventDescription();
                    case "2" -> userEventManagementController.requestToChangeAnEventAttributes();
                    case "3" -> userEventManagementController.requestToCancelAnEvent();
                    case "4" -> { break label; }
                    default -> System.out.println("Invalid input. Please try again.");
                }

            } while (true);
    }

    public static void handleUserProfileAction(User user) throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);
        UserProfileController userProfileController = new UserProfileController(user);

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
                case "1" -> userProfileController.viewProfile();
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
                case "1" -> userProfileController.updateName();
                case "2" -> userProfileController.updateSurname();
                case "3" -> userProfileController.updateAge();
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
                case "1" -> userProfileController.updateUsername();
                case "2" -> userProfileController.updateEmail();
                case "3" -> userProfileController.updatePassword();
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
                case "1" -> userProfileController.setPaymentMethod();
                case "2" -> userProfileController.updatePaymentMethod();
                case "3" -> userProfileController.removePaymentMethod();
                case "4" -> userProfileController.updateCreditCard();
                case "5" -> userProfileController.updatePayPal();
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
                case "1" -> adminUserController.viewUsers();
                case "2" -> adminUserController.searchUser();
                case "3" -> adminUserController.addUser();
                case "4" -> adminUserController.removeUser();
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
                     8. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> adminEventController.seeRequests();
                case "2" -> adminEventController.removeRequest();
                case "3" -> adminEventController.removeAllRequests();
                case "4" -> adminEventController.viewEvents();
                case "5" -> adminEventController.addEvent();
                case "6" -> adminEventController.editEvent();
                case "7" -> adminEventController.removeEvent();
                case "8" -> { break label; }
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
                case "1" -> adminExtraController.resetPassword();
                case "2" -> adminExtraController.resetDatabase();
                case "3" -> adminExtraController.generateDefaultDatabase();
                case "4" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

}