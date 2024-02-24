package main.java.DomainModel;

public class CreditCard extends CreditCardPaymentStrategy {
    // TODO: implement this class

    // owner data
    private String ownerName, ownerSurname;

    // card data
    private String cardNumber, cardType, cardExpirationDate, cardSecurityCode;

    // constructors
    public CreditCard(String ownerName, String ownerSurname, String cardNumber, String cardType, String cardExpirationDate, String cardSecurityCode) {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardExpirationDate = cardExpirationDate;
        this.cardSecurityCode = cardSecurityCode;
    }

    // getters - owner data
    public String getOwnerName() { return ownerName; }
    public String getOwnerSurname() { return ownerSurname; }

    // getters - card data
    public String getCardNumber() { return cardNumber; }
    public String getCardType() { return cardType; }
    public String getCardExpirationDate() { return cardExpirationDate; }
    public String getCardSecurityCode() { return cardSecurityCode; }

    public String getCreditCardData() {
        return "Owner: " + ownerName + " " + ownerSurname + "\nCard number: " + cardNumber + "\nCard type: " + cardType + "\nExpiration date: " + cardExpirationDate + "\nSecurity code: " + cardSecurityCode;
    }

}
