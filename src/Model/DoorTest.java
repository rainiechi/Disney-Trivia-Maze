package Model;
import SQLite.DBRetriever;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;
public class DoorTest {

    private Door door;
    private Player player;
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
     * used to test the LockedDoor methods when the user has a soul stone.
     */
    @Test
    public void testLockedDoorWithSoulStone() {
        player.setSoulStone(true);
        door.setAttempted(true);

        // Redirect standard output to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        door.lockedDoor(player);
        System.setOut(System.out);

        assertEquals("Would you like to use the Soul Stone to attempt this door again?",
                outContent.toString().trim());
    }

    /**
     * used to test LockedDoor when the user has no soul stone.
     */
    @Test
    public void testLockedDoorWithoutSoulStone() {
        player.setSoulStone(false);
        door.setAttempted(true);

        // Redirect standard output to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        door.lockedDoor(player);
        System.setOut(System.out);

        assertEquals("Door has been attempted.", outContent.toString().trim());
    }

    /**
     * used to check the checkPlayerAnswer methods when the user enter the right answer.
     */
    @Test
    public void testCheckPlayerAnswerCorrect() {
        door.setPlayerMyAnswer(door.getQuestionObject().getMyAnswer());
        door.checkPlayerAnswer();

        assertTrue(door.getMyUnlock());
        assertTrue(door.getAttempt());
    }

    /**
     * used to check the checkPlayerAnswer methods when the user enter the wrong answer.
     */
    @Test
    public void testCheckPlayerAnswerIncorrect() {
        door.setPlayerMyAnswer("wrong answer");
        door.checkPlayerAnswer();

        assertFalse(door.getMyUnlock());
        assertTrue(door.getAttempt());
    }

    /**
     * testing all the getter methods.
     */
    @Test
    public void testGetOption1() {
        assertNotNull(door.getQuestionObject().getMyOption1());
    }

    @Test
    public void testGetMyOption2() {
        assertNotNull(door.getQuestionObject().getMyOption2());
    }

    @Test
    public void testGetMyOption3() {
        assertNotNull(door.getQuestionObject().getMyOption3());
    }

    @Test
    public void testGetMyOption4() {
        assertNotNull(door.getQuestionObject().getMyOption4());
    }

    /**
     * Testing all the setter methods.
     */
    @Test
    public void testSetMyUnlock() {
        door.setMyUnlock(true);
        assertTrue(door.getMyUnlock());
    }

    @Test
    public void testSetAttempted() {
        door.setAttempted(true);
        assertTrue(door.getAttempt());
    }

    @Test
    public void testSetPlayerMyAnswer() {
        door.setPlayerMyAnswer("res.player answer");
        assertEquals("res.player answer", door.getPlayerMyAnswer());
    }

    @Test
    public void testGetQAnswer() {
        assertNotNull(door.getQuestionObject().getMyAnswer());
    }

}
