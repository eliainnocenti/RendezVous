package main.java.DomainModel;

import java.util.Scanner;

public class CreditCard implements PaymentStrategy {

    // constants
    private static final float REFUND_PERCENTAGE = 0.9f;
    private static final float COMMISSION_PERCENTAGE = 0.01f;

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

    // getters - constants
    public static float getRefundPercentage() { return REFUND_PERCENTAGE; }
    public static float getCommissionPercentage() { return COMMISSION_PERCENTAGE; }

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
    public void pay(Event event) {

        // SIMULATE PAYMENT

        float amount = event.getFee();

        if (amount < 0) throw new IllegalArgumentException("\nInvalid amount");
        if (amount == 0) return;

        amount += amount * COMMISSION_PERCENTAGE;

        System.out.println("\nPaying for event: " + event.getName());
        System.out.println("Amount: " + amount + "€ (including " + (COMMISSION_PERCENTAGE * 100) + "% commission)");

        System.out.println("\nConfirm the payment? (yes/no)");

        Scanner scanner = new Scanner(System.in);
        String confirmation = scanner.nextLine().toLowerCase();

        if (!confirmation.equals("yes")) {
            System.out.println("Payment cancelled!");
            return;
        }

        System.out.println("Paying with credit card...");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Enter your credit card security code:");

        String password;
        boolean isTrue;

        do {
            password = scanner.nextLine();
            isTrue = password.equals(cardSecurityCode);
            if (!isTrue) {
                System.out.println("Incorrect security code! Try again:");
            }
        } while (!isTrue);

        System.out.println("Connecting to the bank...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Payment successful!");
    }

    @Override
    public void refund(Event event) {

        // SIMULATE REFUND

        float amount = event.getFee();
        amount *= REFUND_PERCENTAGE;

        System.out.println("\nRefunding the payment...");

        System.out.println("Event: " + event.getCode() + " " + event.getName() + ", User: " + ownerName + " " + ownerSurname);

        System.out.println("Amount refundable: " + amount + "€ (including " + Math.round((1 - REFUND_PERCENTAGE) * 1000) / 10.0 + "% commission)");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Connecting to the bank...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.err.println("Error: " + e.getMessage());
        }

        System.out.println("Refund successful!");

    }

    @Override
    public String getPaymentMethod() {
        return "Credit Card";
    }

    @Override
    public String getPaymentData() {
        return getCreditCardData();
    }

}