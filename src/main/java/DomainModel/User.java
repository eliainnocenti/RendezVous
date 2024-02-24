package main.java.DomainModel;

public class User {
    // TODO: implement this class

    // personal data
    private String name, surname;
    private int age;

    // login data
    private String username, email, password;

    // payment data
    private CreditCard creditCard;

    // constructors
    public User(String name, String surname, int age, String username, String email, String password, CreditCard creditCard) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creditCard = creditCard;
    }
    public User(String name, String surname, int age, String username, String email, String password, String cardNumber, String cardType, String cardExpirationDate, String cardSecurityCode) {
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.username = username;
        this.email = email;
        this.password = password;
        this.creditCard = new CreditCard(name, surname, cardNumber, cardType, cardExpirationDate, cardSecurityCode);
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
    public String getPersonalData() { return "Name: " + name + "\nSurname: " + surname + "\nAge: " + age; }

    // getters - login data
    public String getUsername() { return username; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public String getLoginData() { return "Username: " + username + "\nEmail: " + email + "\nPassword: " + password; }

    // getters - payment data
    public String getCardNumber() { return creditCard.getCardNumber(); }
    public String getCardType() { return creditCard.getCardType(); }
    public String getCardExpirationDate() { return creditCard.getCardExpirationDate(); }
    public String getCardSecurityCode() { return creditCard.getCardSecurityCode(); }
    public String getPaymentData() { return creditCard.getCreditCardData(); }

    // setters - personal data
    public void setName(String name) { this.name = name; }
    public void setSurname(String surname) { this.surname = surname; }
    public void setAge(int age) { this.age = age; }

    // setters - login data // TODO - very careful implementation !!! -> it may be an issue
    public void updateUsername(String username) {}
    public void updateEmail(String email) {}
    public void updatePassword(String password) {}

    // setters - payment data
    public void setCreditCard(CreditCard creditCard) { this.creditCard = creditCard; }
    public void setCreditCard(String cardNumber, String cardType, String cardExpirationDate, String cardSecurityCode) {
        this.creditCard = new CreditCard(this.name, this.surname, cardNumber, cardType, cardExpirationDate, cardSecurityCode);
    }

}