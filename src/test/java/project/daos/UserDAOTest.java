package project.daos;


import org.javalite.activejdbc.Base;
import org.javalite.activejdbc.*;
import org.javalite.activejdbc.LazyList;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

import project.DAO.UserDAO;
import project.models.User;




public class UserDAOTest {
    //DB database = new DB("4inaRow_test");
    @BeforeAll
    public static void before() {
        Base.open();
        Base.openTransaction();
    }
    @AfterAll
    public static void after() {
        Base.rollbackTransaction();
        Base.close();
    }
    @Test
    public void createNewUser() {
        long preCount = User.count();
        User user = UserDAO.createUser("Juan","1234",
                "juan@mail.com","asdasda");

        assertEquals(User.count(),preCount+1);
    }
    @Test
    public void changeDisplayNameWorks(){

        User user = UserDAO.createUser("Juan2","1234",
                "juan2@mail.com","oldName");
        UserDAO.modifyDisplayName("Juan2","newName");
        assertEquals("newName",UserDAO.findByName("Juan2").getDisplayName());
    }
}
