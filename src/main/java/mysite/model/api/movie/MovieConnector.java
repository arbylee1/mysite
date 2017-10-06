package mysite.model.api.movie;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.security.SecureRandom;
import java.security.MessageDigest;


public class MovieConnector {
    static private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static private final String DB_URL = "jdbc:mysql://localhost/movie";
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


    public void registerUser(String username, String password) throws UserExistsException {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            SecureRandom random = new SecureRandom();
            byte[] salt = new byte[25];
            random.nextBytes(salt);
            md.update(password.getBytes());
            md.update(salt);
            String hash = new String(md.digest());
            sql = String.format("INSERT INTO users (username,hash,salt)\n" +
                    "VALUES (\'%s\',\'%s\',\'%s\');", username, hash, new String(salt));
            stmt.executeQuery(sql);

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        }
    }

    public User login(String username, String password) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
            String sql;
            sql = "SELECT id, first, last, age FROM Employees";

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
