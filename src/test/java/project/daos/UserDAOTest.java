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
        UserDAO.createUser("Juan","1234",
                "juan@mail.com","asdasda");

        assertEquals(User.count(),preCount+1);
    }
    @Test
    public void changeDisplayNameWorks(){

        UserDAO.createUser("Juan2","1234",
                "juan2@mail.com","oldName");
        UserDAO.modifyDisplayName("Juan2","newName");
        assertEquals("newName",UserDAO.findByName("Juan2").get("display_name"));
    }
    @Test
    public void getAllUsersWorks(){
        UserDAO.createUser("Juan2","1234",
                "juan2@mail.com","oldName");
        UserDAO.createUser("Juan4","1234",
                "juan4@mail.com","old1Name");
        LazyList<User> userList =UserDAO.getAllUsers();
        assertEquals(userList.size() , User.count());
    }
    @Test
    public void modifyPasswordWOrks() throws Exception {
        String oldPassword = "12345";
        String newPassword = "newPass21345";
        UserDAO.createUser("Juan3",oldPassword,
                "juan3@mail.com","old3Name");
        UserDAO.modifyPassword("Juan3",oldPassword,newPassword);
        assertEquals(newPassword,User.findFirst("username = ?", "Juan3").get("password"));

    }
}
