package main.java.BusinessLogic;

import main.java.ORM.AdminDAO;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.SQLException;

public class AdminExtraController {

    public void resetPassword(String newPassword) throws SQLException, ClassNotFoundException {

        AdminDAO adminDAO = new AdminDAO();

        adminDAO.updatePassword(newPassword);

    }

    public void resetDatabase() throws SQLException, ClassNotFoundException {

        StringBuilder sql_tmp = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/sql/reset.sql"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sql_tmp.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        String sql = sql_tmp.toString();

        AdminDAO adminDAO = new AdminDAO();

        adminDAO.resetDatabase(sql);

    }

    public void generateDefaultDatabase() throws SQLException, ClassNotFoundException {

        resetDatabase();

        StringBuilder sql_tmp = new StringBuilder();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("src/main/sql/default.sql"))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                sql_tmp.append(line).append("\n");
            }
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return;
        }

        String sql = sql_tmp.toString();

        AdminDAO adminDAO = new AdminDAO();

        adminDAO.generateDefaultDatabase(sql);

    }

}