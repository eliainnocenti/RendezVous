package main.java.DomainModel;

public class PayPal implements PaymentStrategy {

    // owner data
    private String ownerName, ownerSurname;

    // account data
    private String uniqueCode, accountEmail, accountPassword; // TODO - implement code generation (the code is a unique identifier for the account)

    // constructors
    public PayPal(String ownerName, String ownerSurname, String uniqueCode, String accountEmail, String accountPassword) {
        this.ownerName = ownerName;
        this.ownerSurname = ownerSurname;
        this.uniqueCode = uniqueCode;
        this.accountEmail = accountEmail;
        this.accountPassword = accountPassword;
    }

    // getters - owner data
    public String getOwnerName() { return ownerName; }
    public String getOwnerSurname() { return ownerSurname; }

    // getters - account data
    public String getUniqueCode() { return uniqueCode; }
    public String getAccountEmail() { return accountEmail; }
    public String getAccountPassword() { return accountPassword; }

    public String getPayPalData() {
        return "Owner: " + ownerName + " " + ownerSurname + "\nUnique code: " + uniqueCode + "\nAccount email: " + accountEmail + "\nAccount password: " + accountPassword;
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
        return "PayPal";
    }

    @Override
    public String getPaymentData() {
        return getPayPalData();
    }

}