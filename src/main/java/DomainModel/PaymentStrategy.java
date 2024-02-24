package main.java.DomainModel;

public interface PaymentStrategy {
    // TODO: implement this interface

    void pay(Participation participation, float fee);

    String getPaymentMethod();

    float calculateFee(Event event);
}
