package main.java.DomainModel;

public class CreditCard implements PaymentStrategy {

    // owner data
    private String ownerName, ownerSurname;

    // card data
    private String cardNumber, cardExpirationDate, cardSecurityCode;

    // constructors
    public CreditCard(String ownerName, String ownerSurname, String cardNumber, String cardExpirationDate, String cardSecurityCode) {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.cardNumber = cardNumber;
        this.cardExpirationDate = cardExpirationDate;
        this.cardSecurityCode = cardSecurityCode;
    }

    // getters - owner data
    public String getOwnerName() { return ownerName; }
    public String getOwnerSurname() { return ownerSurname; }

    // getters - card data
    public String getCardNumber() { return cardNumber; }
    public String getCardExpirationDate() { return cardExpirationDate; }
    public String getCardSecurityCode() { return cardSecurityCode; }

    public String getCreditCardData() {
        return "Owner: " + ownerName + " " + ownerSurname + "\nCard number: " + cardNumber + "\nExpiration date: " + cardExpirationDate + "\nSecurity code: " + cardSecurityCode;
    }

    @Override
    public void pay(Participation participation) {
        // TODO: implement this method
    }

    @Override
    public void refund() {
        // TODO: implement this method
        // refund all the people that paid for the event
    }

    @Override
    public String getPaymentMethod() {
        return "Credit card";
    }

    @Override
    public String getPaymentData() {
        return getCreditCardData();
    }

}