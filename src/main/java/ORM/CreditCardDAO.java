package main.java.ORM;

import main.java.DomainModel.CreditCard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class CreditCardDAO {

    public void addCreditCard(int userId, String ownerName, String ownerSurname, String cardNumber, String cardExpirationDate, String cardSecurityCode) {
        // TODO: implement this method

    }

    public void addCreditCard(String username, String ownerName, String ownerSurname, String cardNumber, String cardExpirationDate, String cardSecurityCode) {
        // TODO: implement this method

    }

    public void addCreditCard(int userId, String cardNumber, String cardExpirationDate, String cardSecurityCode) {
        // TODO: implement this method

    }

    public void addCreditCard(String username, String cardNumber, String cardExpirationDate, String cardSecurityCode) {
        // TODO: implement this method

    }

    public void removeCreditCard(String cardNumber) {
        // TODO: implement this method

    }

    public CreditCard getCreditCardInfo(String cardNumber) {
        // TODO: implement this method

        return null;

    }

    public CreditCard getCreditCard(int userId) {
        // TODO: implement this method

        return null;

    }

    public CreditCard getCreditCard(String username) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<CreditCard> getAllCreditCards() {
        // TODO: implement this method

        return null;

    }

}