package main.java.DomainModel;

public class Participation {
    // TODO: implement this class

    // participation data
    private User user;
    private Event event;
    private PaymentStrategy paymentMethod;
    private boolean paid;
    private float fee;

    // constructors
    public Participation(User user, Event event, PaymentStrategy paymentMethod, boolean paid, float fee) {
        this.user = user;
        this.event = event;
        this.paymentMethod = paymentMethod;
        this.paid = paid;
        this.fee = fee;
    }

    // getters
    public User getUser() { return user; }
    public Event getEvent() { return event; }
    public PaymentStrategy getPaymentMethod() { return paymentMethod; }
    public boolean isPaid() { return paid; }
    public float getFee() { return fee; }

    public String getParticipationData() {
        return "User: " + user.getUsername() + "Event: " + event.getName() + "\nPayment method: " + paymentMethod;
    }

}
