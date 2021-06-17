package project.daos;


import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.LazyList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import project.DAO.UserDAO;
import project.models.User;

import static org.junit.Assert.assertEquals;


public class UserDAOTest {
    @Before
    public void before() {
        Base.open();
        Base.openTransaction();
    }
    @After
    public void after() {
        Base.rollbackTransaction();
        Base.close();
    }
    @Test
    public void createNewUser() {
        long preCount = User.count();
        User user = UserDAO.createUser("Juan","1234",
                "juan@mail.com","asdasda");

        assertEquals("Count of users increased by 1", (long) preCount + 1, (long) User.count());
    }
}
