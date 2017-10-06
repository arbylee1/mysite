package mysite.model.api.movie;
import org.junit.Test;

public class MovieConnectorTest {
    MovieConnector connector = MovieConnector.getConnector();

    @Test
    public void testRegister() {
        connector.registerUser("arbylee", "1234");
    }
}
