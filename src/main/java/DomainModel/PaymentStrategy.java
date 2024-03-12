package main.java.DomainModel;

public interface PaymentStrategy {

    // methods
    void pay(Event event);
    void refund(Event event);

    // getters
    String getPaymentMethod();
    String getPaymentData();

}