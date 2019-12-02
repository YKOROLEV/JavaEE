import model.User;
import org.junit.Before;
import org.junit.Test;
import service.UserService;

import static org.junit.Assert.*;

public class UserHibernateDaoTest {

    private UserService userService = UserService.getInstance();

    private static final User ADMIN = new User("admin", "tomato", "123");
    private static final User USER = new User("user", "ifactor", "123");

    @Before
    public void init() {
        userService.deleteAll();
        userService.save(ADMIN);
        userService.save(USER);
    }

    @Test
    public void hasByIdTest() {
        assertTrue(userService.hasById(ADMIN.getId()));
    }

    @Test
    public void hasByIdFakeTest() {
        assertFalse(userService.hasById(0L));
    }

    @Test
    public void hasByLoginTest() {
        assertTrue(userService.hasByLogin(ADMIN.getLogin()));
    }

    @Test
    public void hasByLoginFakeTest() {
        assertFalse(userService.hasByLogin("FAKE"));
    }

    @Test
    public void deleteByLoginTest() {
        assertTrue(userService.deleteById(ADMIN.getId()));
        assertFalse(userService.findById(ADMIN.getId()).isPresent());
    }

    @Test
    public void deleteByLoginFakeTest() {
        assertFalse(userService.deleteByLogin("FAKE"));
    }
}