package main.java.ORM;

import main.java.DomainModel.PayPal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PayPalDAO {

    public void addPayPalAccount(int userId, String ownerName, String ownerSurname, String email, String password) {
        // TODO: implement this method

    }

    public void addPayPalAccount(String username, String ownerName, String ownerSurname, String email, String password) {
        // TODO: implement this method

    }

    public void addPayPalAccount(int userId, String email, String password) {
        // TODO: implement this method

    }

    public void addPayPalAccount(String username, String email, String password) {
        // TODO: implement this method

    }

    public void removePayPalAccount(String email) {
        // TODO: implement this method

    }

    public PayPal getPayPalAccountInfo(String email) {
        // TODO: implement this method

        return null;

    }

    public PayPal getPayPalAccount(int userId) {
        // TODO: implement this method

        return null;

    }

    public PayPal getPayPalAccount(String username) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<PayPal> getAllPayPalAccounts() {
        // TODO: implement this method

        return null;

    }

}