package CRUDOperation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:postgresql://localhost:54321/aayushdb";
    private static final String USER = "postgres";
    private static final String PASS = "rabnam";
    public static Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver"); // load driver
            return DriverManager.getConnection(URL, USER, PASS);
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
            e.printStackTrace();
        }
        return null; // return null if connection fails
    }
}
