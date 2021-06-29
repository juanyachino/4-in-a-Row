package project.logic;

import org.javalite.activejdbc.Base;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import project.DAO.UserDAO;





public class GameTest {
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
    public void createNewGame() {

        UserDAO.createUser("Juan3","1234",
                "juan3@mail.com","juantateti");
        UserDAO.createUser("Juan2","1234",
                "juan2@mail.com","pepeargento");
        Game actualGame = new Game("juantateti","pepeargento");
        assertNotNull(actualGame.getBoard());
        assertEquals(actualGame.getActualPlayer().getDisplayName(),"juantateti");
        actualGame.changeTurn();
        assertEquals(actualGame.getActualPlayer().getDisplayName(),"pepeargento");

    }
    @Test
    public void createGameJSONWorks(){
        Game actualGame = new Game("juantateti","pepeargento");
        JSONObject theJSON = actualGame.createGameJSON();
        assertNotNull(theJSON);
    }
}