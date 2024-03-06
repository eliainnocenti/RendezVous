package main.java.DomainModel;

public class Participation {

    // participation data
    private User user;
    private Event event;
    private String paymentMethod;

    // constructors
    public Participation(User user, Event event, String paymentMethod) {
        this.user = user;
        this.event = event;
        this.paymentMethod = paymentMethod;
    }

    // getters
    public User getUser() { return user; }
    public Event getEvent() { return event; }
    public String getPaymentMethod() { return paymentMethod; }

    public String getParticipationData() {
        return "User: " + user.getUsername() + "Event: " + event.getName() + "\nPayment method: " + paymentMethod;
    }

}