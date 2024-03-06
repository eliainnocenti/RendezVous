package main.java.DomainModel;

public interface PaymentStrategy {

    // methods
    void pay(Participation participation);
    void refund(); // TODO: Event event or Participation participation?

    // getters
    String getPaymentMethod();
    String getPaymentData();

}