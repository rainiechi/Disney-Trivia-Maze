package Model;
import SQLite.DBRetriever;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * DoorTest tests methods in Door class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sanga
 * @version 6/5/23
 */
public class DoorTest {
    /** Door object */

    private Door door;
    /** Player object */
    private Player player;
    /** QuestionRecord object */
    private QuestionRecord questionRecord;

    /**
     * This method is used to set up the initial condition for all the tests.
     */
    @BeforeEach
    public void setUp() {
        DBRetriever rt = new DBRetriever();
        questionRecord = new QuestionRecord();
        door = new Door(questionRecord);
        player = new Player();

    }

    /**
     * testing all the getter methods.
     */
    @Test
    public void testGetOption1() {
        assertNotNull(door.getQuestionObject().getMyOption1());
    }
    /**
     * testing all the getter methods.
     */
    @Test
    public void testGetMyOption2() {
        assertNotNull(door.getQuestionObject().getMyOption2());
    }
    /**
     * testing all the getter methods.
     */
    @Test
    public void testGetMyOption3() {
        assertNotNull(door.getQuestionObject().getMyOption3());
    }
    /**
     * testing all the getter methods.
     */
    @Test
    public void testGetMyOption4() {
        assertNotNull(door.getQuestionObject().getMyOption4());
    }
    /**
     * testing all the getter methods.
     */
    @Test
    public void testGetQAnswer() {
        assertNotNull(door.getQuestionObject().getMyAnswer());
    }

    /**
     * Testing all the setter methods.
     */
    @Test
    public void testSetMyUnlock() {
        door.setMyUnlock(true);
        assertTrue(door.isUnlocked());
    }
    /**
     * Testing all the setter methods.
     */
    @Test
    public void testSetAttempted() {
        door.setAttempted(true);
        assertTrue(door.isAttempted());
    }

}
