package main.java.DomainModel;

public abstract class CreditCardPaymentStrategy implements PaymentStrategy {

    @Override
    public void pay(Participation participation, float fee) {

    }

    @Override
    public String getPaymentMethod() {
        return "Credit card";
    }

    //@Override
    /* public float calculateFee(Event event) {} */

}
