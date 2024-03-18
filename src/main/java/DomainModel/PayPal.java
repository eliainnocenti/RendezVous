package main.java.DomainModel;

import java.util.Scanner;

public class PayPal implements PaymentStrategy {

    // constants
    private static final float REFUND_PERCENTAGE = 1f;
    private static final float COMMISSION_PERCENTAGE = 0.05f;

    // owner data
    private String ownerName, ownerSurname;

    // account data
    private int uniqueCode;
    private String accountEmail, accountPassword;

    // constructors
    public PayPal(String ownerName, String ownerSurname, String uniqueCode, String accountEmail, String accountPassword) {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.uniqueCode = Integer.parseInt(uniqueCode);
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
    }

    // getters - constants
    public static float getRefundPercentage() { return REFUND_PERCENTAGE; }
    public static float getCommissionPercentage() { return COMMISSION_PERCENTAGE; }

    // getters - owner data
    public String getOwnerName() { return ownerName; }
    public String getOwnerSurname() { return ownerSurname; }

    // getters - account data
    public int getUniqueCode() { return uniqueCode; }
    public String getAccountEmail() { return accountEmail; }
    public String getAccountPassword() { return accountPassword; }

    public String getPayPalData() {
        return "Owner: " + ownerName + " " + ownerSurname + "\nUnique code: " + uniqueCode + "\nAccount email: " + accountEmail + "\nAccount password: " + accountPassword;
    }

    @Override
    public void pay(Event event) {

        // SIMULATE PAYMENT

        float amount = event.getFee();
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

        System.out.println("Paying with PayPal...");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Connecting with the server...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Payment successful!");
    }

    @Override
    public void refund(Event event) {

        // SIMULATE REFUND

        float amount = event.getFee();
        amount += amount * REFUND_PERCENTAGE;

        System.out.println("Refunding the payment...");

        System.out.println("Event: " + event.getCode() + " " + event.getName() + ", User: " + ownerName + " " + ownerSurname);

        System.out.println("Amount refundable: " + amount + "€ (including " + ((1 - REFUND_PERCENTAGE) * 100) + "% commission)");

        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Connecting to the bank...");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Refund successful!");

    }

    @Override
    public String getPaymentMethod() {
        return "PayPal";
    }

    @Override
    public String getPaymentData() {
        return getPayPalData();
    }

}