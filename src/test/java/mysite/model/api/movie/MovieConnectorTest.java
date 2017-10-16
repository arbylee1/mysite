package mysite.model.api.movie;
import org.junit.Test;

import java.sql.SQLException;

public class MovieConnectorTest {
    MovieConnector connector = MovieConnector.getConnector();

    @Test
    public void testRegister() {
        System.out.println("Testing user registration");
        System.out.println("Testing add user \"arbylee\" with password \"1234\"");
        try {
            connector.registerUser("arbylee", "1234");
        } catch (SQLException e) {

        }
    }
}
