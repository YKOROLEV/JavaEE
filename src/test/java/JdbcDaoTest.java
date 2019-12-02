import dao.JdbcDaoFactory;
import org.junit.Assert;
import org.junit.Test;

import java.sql.Connection;

public class JdbcDaoTest {

    @Test
    public void createConnectionTest() {
        Connection connection = JdbcDaoFactory.createConnection();
        Assert.assertNotNull(connection);
    }
}