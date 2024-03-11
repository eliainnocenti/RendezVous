package main.java.DomainModel;

public class User {

    // unique identifier
    private int id;

    // personal data
    private String name, surname;
    private int age;

    // login data
    private String username, email, password;

    // payment data
    private PaymentStrategy paymentMethod;

    // constructors
    public User(String name, String surname, int age, String username, String email, String password, PaymentStrategy paymentMethod) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.paymentMethod = paymentMethod;
    }
    public User(int id, String name, String surname, int age, String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        if (paymentMethod.equals("credit card") || paymentMethod.equals("creditcard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("CreditCard")) {
            this.paymentMethod = new CreditCard(name, surname, cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
        } else if (paymentMethod.equals("paypal") || paymentMethod.equals("PayPal")) {
            this.paymentMethod = new PayPal(name, surname, cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
        }
    }
    public User(String name, String surname, int age, String username, String email, String password, String paymentMethod, String cardNumberORuniqueCode, String cardExpirationDateORaccountEmail, String cardSecurityCodeORaccountPassword) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        if (paymentMethod.equals("credit card") || paymentMethod.equals("creditcard") || paymentMethod.equals("Credit Card") || paymentMethod.equals("CreditCard")) {
            this.paymentMethod = new CreditCard(name, surname, cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
        } else if (paymentMethod.equals("paypal") || paymentMethod.equals("PayPal")) {
            this.paymentMethod = new PayPal(name, surname, cardNumberORuniqueCode, cardExpirationDateORaccountEmail, cardSecurityCodeORaccountPassword);
        }
    }
    public User(int id, String name, String surname, int age, String username, String email, String password) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String name, String surname, int age, String username, String email, String password) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }
    public User(String username, String email, String password, PaymentStrategy paymentMethod) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.paymentMethod = paymentMethod;
    }

    // getters - unique identifier
    public int getId() { return id; }

    // getters - personal data
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getAge() { return age; }
    public String getPersonalData() { return "Name: " + name + "\nSurname: " + surname + "\nAge: " + age; }

    // getters - login data
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getLoginData() { return "Username: " + username + "\nEmail: " + email + "\nPassword: " + password; }

    // getters - payment data
    public String getPaymentMethod() {
        if (this.paymentMethod != null) {
            return paymentMethod.getPaymentMethod();
        } else {
            return null;
        }
    }
    public String getPaymentData() { return paymentMethod.getPaymentData(); }
    public String[] getPaymentCode() {
        String[] paymentCode = {"null", "null"};
        if (paymentMethod instanceof CreditCard) {
            paymentCode[0] = ((CreditCard) paymentMethod).getCardNumber();
            paymentCode[1] = "null";
            return paymentCode;
        } else if (paymentMethod instanceof PayPal) {
            int tmp = ((PayPal) paymentMethod).getUniqueCode();
            paymentCode[0] = "null";
            paymentCode[1] = Integer.toString(tmp);
            return paymentCode;
        }
        return paymentCode;
    }

    // setters - unique identifier
    public void setId(int id) { this.id = id; }

    // setters - personal data
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setAge(int age) { this.age = age; }

    // setters - login data
    public void updateUsername(String username) { this.username = username; }
    public void updateEmail(String email) { this.email = email; }
    public void updatePassword(String password) { this.password = password; }

    // setters - payment data
    public void setPaymentMethod(PaymentStrategy paymentMethod) { this.paymentMethod = paymentMethod; }
    public void setCreditCard(CreditCard creditCard) { this.paymentMethod = creditCard; }
    public void setCreditCard(String cardNumber, String cardType, String cardExpirationDate, String cardSecurityCode) {
        this.paymentMethod = new CreditCard(this.name, this.surname, cardNumber, cardExpirationDate, cardSecurityCode);
    }
    public void setPayPal(PayPal payPal) { this.paymentMethod = payPal; }
    public void setPayPal(String uniqueCode, String accountEmail, String accountPassword) {
        this.paymentMethod = new PayPal(this.name, this.surname, uniqueCode, accountEmail, accountPassword);
    }

}