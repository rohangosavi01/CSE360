import java.sql.*;

public class LoginTracker {
    private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost/your_database_name";
    private final String USER = "your_database_username";
    private final String PASS = "your_database_password";
    
    public void logLogin(String username, boolean success) {
        try {
            // Register JDBC driver
            Class.forName(JDBC_DRIVER);

            // Open a connection
            Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);

            // Create a prepared statement
            String sql = "INSERT INTO login_logs (username, login_time, success) VALUES (?, NOW(), ?)";
            PreparedStatement stmt = conn.prepareStatement(sql);

            // Set the parameters
            stmt.setString(1, username);
            stmt.setBoolean(2, success);

            // Execute the statement
            stmt.executeUpdate();

            // Close the resources
            stmt.close();
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
