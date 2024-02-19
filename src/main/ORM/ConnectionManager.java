package main.ORM;
import java.sql.*;

public class ConnectionManager {

    private static final String url = "jdbc:postgresql://localhost:5432/RendezVous_db";
    private static final String username = "RendezVous_admin";
    private static final String password = "admin";
    private static Connection connection = null;

    public ConnectionManager(){}

    static public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        if (connection == null)
            try {
                connection = DriverManager.getConnection(url, username, password);
            } catch (SQLException e) {
                e.printStackTrace(); // TODO: handle exception
            }
        return connection;
    }
}