package main.java;

import main.java.BusinessLogic.*;
import main.java.DomainModel.*;

import java.sql.SQLException;
import java.util.Scanner;

// FIXME: the password attribute must be contained into "" (?) because it's important if there's capital letters.

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
                case "2" -> loginController.register();
                case "3" -> {
                    if (loginController.adminLogin() != null)
                        handleAdminAction();
                }
                case "4" -> System.exit(0);
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleUserAction(User user) {

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

    public static void handleEventPageAction(User user) {

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

    public static void handleEventManagementAction(User user) {

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

    public static void handleEventEditorAction(User user) {

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

    public static void handleUserProfileAction(User user) {

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

    public static void handleProfileEditorAction(User user) {

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

    public static void handleProfileEditorPersonalDataAction(User user) {

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

    public static void handleProfileEditorLoginDataAction(User user) {

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

    public static void handleProfileEditorPaymentDataAction(User user) {

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
                     3. Update the credit card
                     4. Update the PayPal account
                     5. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> userProfileController.setPaymentMethod();
                case "2" -> userProfileController.updatePaymentMethod();
                case "3" -> userProfileController.updateCreditCard();
                case "4" -> userProfileController.updatePayPal();
                case "5" -> { break label; }
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
        AdminUsersController adminUsersController = new AdminUsersController();

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
                case "1" -> adminUsersController.viewUsers();
                case "2" -> adminUsersController.searchUser();
                case "3" -> adminUsersController.addUser();
                case "4" -> adminUsersController.removeUser();
                case "5" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

    public static void handleAdminEventsAction() {

        Scanner scanner = new Scanner(System.in);
        AdminEventsController adminEventsController = new AdminEventsController();

        String input;

        label:
        do {

            System.out.println(
                    """
                     
                     EVENTS MANAGEMENT
                     1. See requests
                     2. View events
                     3. Create an event
                     4. Edit an event
                     5. Cancel an event
                     6. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> adminEventsController.seeRequests();
                case "2" -> adminEventsController.viewEvents();
                case "3" -> adminEventsController.addEvent();
                case "4" -> adminEventsController.editEvent();
                case "5" -> adminEventsController.removeEvent();
                case "6" -> { break label; }
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
                     3. Go back
                    """
            );

            input = scanner.nextLine();

            switch (input) {
                case "1" -> adminExtraController.resetPassword();
                case "2" -> adminExtraController.resetDatabase();
                case "3" -> { break label; }
                default -> System.out.println("Invalid input. Please try again.");
            }

        } while (true);

    }

}