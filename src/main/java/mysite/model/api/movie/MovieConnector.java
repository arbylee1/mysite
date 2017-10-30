package mysite.model.api.movie;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.List;
import java.security.SecureRandom;
import java.security.MessageDigest;


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
            stmt.executeUpdate(sql);
            stmt.close();
            conn.close();
        } catch (NoSuchAlgorithmException nsae) {
            nsae.printStackTrace();
        } catch (ClassNotFoundException cnfe) {
            cnfe.printStackTrace();
        } finally {
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
                try {
                    if (conn != null)
                        conn.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }
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
            sql = "SELECT * FROM TABLE WHERE ID = " + username;
            ResultSet rs = stmt.executeQuery(sql);
            stmt.close();
            conn.close();
            String salt = rs.getString("salt");
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            md.update(salt.getBytes());
            String hash = new String(md.digest());
            if (hash.equals(rs.getString("hash"))) {
                return new User(rs.getString("email"),rs.getString("name"),rs.getString("major"),
                        rs.getString("interests"), rs.getBoolean("isBanned"),rs.getBoolean("isAdmin"));
            }

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
