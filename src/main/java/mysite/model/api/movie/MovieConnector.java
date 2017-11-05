package mysite.model.api.movie;

import java.sql.*;
import java.util.List;
import org.springframework.security.crypto.bcrypt.*;


public class MovieConnector {
    static private final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static private final String DB_URL = "jdbc:mysql://localhost/movie?serverTimezone=UTC";
    static private final String USER = "username";
    static private final String PASS = "password";

    private static MovieConnector connector;
    private MovieConnector() {}
    public static MovieConnector getConnector() {
        if (connector == null) {
            connector = new MovieConnector();
        }
        return connector;
    }


    public void registerUser(String username, String password) throws SQLException {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName(JDBC_DRIVER);
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.createStatement();
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());
            String sql = String.format("INSERT INTO users (username,hash)\n" +
                    "VALUES (\'%s\',\'%s\');", username, hash);
            statement.executeUpdate(sql);
            statement.close();
            connection.close();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        closeConnection(connection, statement);
    }

    public User login(String username, String password) {
        Connection connection = null;
        Statement statement = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL,USER,PASS);
            statement = connection.createStatement();
            String sql = "SELECT * FROM users WHERE username=\"" + username + "\"";
            ResultSet rs = statement.executeQuery(sql);
            rs.next();
            if (BCrypt.checkpw(password, rs.getString("hash"))) {
                User ret = new User(rs.getString("email"),rs.getString("name"),rs.getString("major"),
                        rs.getString("interests"), rs.getBoolean("isBanned"),rs.getBoolean("isAdmin"));
                closeConnection(connection, statement);
                return ret;
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
        closeConnection(connection, statement);
        return null;
    }

    private void closeConnection(Connection connection, Statement statement) {
        try {
            if (statement != null)
                statement.close();
        } catch (SQLException se2) {
            try {
                if (connection != null)
                    connection.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    public void saveUserProfile() {

    }

    public void banUser() {

    }

    public void unbanUser() {

    }

    public List<User> getAllUsernamesAdminBanStatuses() {
        return null;
    }

    public List<String> getAllUsernames () {
        return null;
    }

    public List<Review> getAllUserReviews(String username) {
        return null;
    }

    public List<Review> getAllMovieReviews(String movie) {
        return null;
    }


}
