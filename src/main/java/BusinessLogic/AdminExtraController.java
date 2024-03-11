package main.java.BusinessLogic;

import main.java.ORM.ConnectionManager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class AdminExtraController {

    public void resetPassword() throws SQLException, ClassNotFoundException {

        Scanner scanner = new Scanner(System.in);

        System.out.println("\nNew admin password: ");
        String newPassword;
        do {
            newPassword = scanner.nextLine();
        } while (newPassword.isEmpty());

        System.out.println("Confirm new admin password: ");
        String confirmPassword;

        do {
            confirmPassword = scanner.nextLine();
        } while (!newPassword.equals(confirmPassword));

        Connection connection = ConnectionManager.getConnection();
        String sql = String.format("UPDATE \"User\" SET password = '%s' WHERE username = 'ADMIN'", newPassword);

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Password reset successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void resetDatabase() throws SQLException, ClassNotFoundException {

        StringBuilder sql = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/sql/reset.sql"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sql.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("\nDatabase reset successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void generateDefaultDatabase() throws SQLException, ClassNotFoundException {

        resetDatabase();

        StringBuilder sql = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/sql/default.sql"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sql.append(line).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Connection connection = ConnectionManager.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql.toString());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Default database generated successfully.");
        } catch (SQLException e) {
            e.printStackTrace();

        }

    }

}