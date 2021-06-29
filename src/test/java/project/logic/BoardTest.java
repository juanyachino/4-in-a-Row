package project.logic;


import org.javalite.activejdbc.Base;

import org.json.JSONObject;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import project.DAO.UserDAO;

public class BoardTest {
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
    public void createNewBoard() {
        Board actualBoard = new Board();
        assertNotNull(actualBoard);
    }
    @Test
    public void createBoardJSONWorks(){
        Board actualBoard = new Board();
        JSONObject theJSON = actualBoard.createBoardJSON();
        assertNotNull(theJSON);
    }
}
