package main.java.DomainModel;

public class User {
    // TODO: implement this class

    // personal data
    private String name, surname;
    private int age;

    // login data
    private String username, email, password;

    // payment data
    private String cardNumber, cardType, cardExpirationDate, cardSecurityCode;

    // constructors
    public User(String name, String surname, int age, String username, String email, String password, String cardNumber, String cardType, String cardExpirationDate, String cardSecurityCode) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.cardNumber = cardNumber;
        this.cardType = cardType;
        this.cardExpirationDate = cardExpirationDate;
        this.cardSecurityCode = cardSecurityCode;
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

    // getters - personal data
    public String getName() { return name; }
    public String getSurname() { return surname; }
    public int getAge() { return age; }

    // getters - login data
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }

    // getters - payment data
    public String getCardNumber() { return cardNumber; }
    public String getCardType() { return cardType; }
    public String getCardExpirationDate() { return cardExpirationDate; }
    public String getCardSecurityCode() { return cardSecurityCode; }

    // setters - personal data
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setAge(int age) { this.age = age; }

    // setters - payment data
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }
    public void setCardType(String cardType) { this.cardType = cardType; }
    public void setCardExpirationDate(String cardExpirationDate) { this.cardExpirationDate = cardExpirationDate; }
    public void setCardSecurityCode(String cardSecurityCode) { this.cardSecurityCode = cardSecurityCode; }

    public String getPersonalData() {
        return "Name: " + name + "\nSurname: " + surname + "\nAge: " + age;
    }

    public String getLoginData() {
        return "Username: " + username + "\nEmail: " + email + "\nPassword: " + password;
    }

    public String getPaymentData() {
        return "Card Number: " + cardNumber + "\nCard Type: " + cardType + "\nCard Expiration Date: " + cardExpirationDate + "\nCard Security Code: " + cardSecurityCode;
    }

}