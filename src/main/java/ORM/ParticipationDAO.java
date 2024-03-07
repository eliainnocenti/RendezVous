package main.java.ORM;

import main.java.DomainModel.Participation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ParticipationDAO {

    public void addParticipation(int userId, int eventId, String paymentMethod) {
        // TODO: implement this method

    }

    public void addParticipation(String username, int eventId, String paymentMethod) {
        // TODO: implement this method

    }

    public void removeParticipation(int userId, int eventId) {
        // TODO: implement this method

    }

    public void removeParticipation(String username, int eventId) {
        // TODO: implement this method

    }

    public void removeAllParticipationsByUser(int userId) {
        // TODO: implement this method

    }

    public void removeAllParticipationsByUser(String username) {
        // TODO: implement this method

    }

    public void removeAllParticipationsByEvent(int eventId) {
        // TODO: implement this method

    }

    public Participation getParticipation(int userId, int eventId) {
        // TODO: implement this method

        return null;

    }

    public Participation getParticipation(String username, int eventId) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Participation> getParticipationsByUser(int userId) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Participation> getParticipationsByUser(String username) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Participation> getParticipationsByEvent(int eventId) {
        // TODO: implement this method

        return null;

    }

    public ArrayList<Participation> getAllParticipations() {
        // TODO: implement this method

        return null;

    }

}