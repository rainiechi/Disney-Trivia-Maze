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

    @BeforeEach
    public void setUp() {
        DBRetriever rt = new DBRetriever();
        rt.resetAllToUnused();
        door = new Door();
        player = new Player(0,0);

    }

    @Test
    public void testLockedDoorWithSoulStone() {
        player.setSoulStone(true);
        door.setAttempted(true);

        // Redirect standard output to a ByteArrayOutputStream
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));

        door.lockedDoor(player);
        System.setOut(System.out);

        assertEquals("Would you like to use the Soul Stone to attempt this door again?", outContent.toString().trim());
    }

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


    @Test
    public void testCheckPlayerAnswerCorrect() {
        door.setPlayerMyAnswer(door.getQAnswer());
        door.checkPlayerAnswer();

        assertTrue(door.getMyUnlock());
        assertTrue(door.getAttempt());
    }

    @Test
    public void testCheckPlayerAnswerIncorrect() {
        door.setPlayerMyAnswer("wrong answer");
        door.checkPlayerAnswer();

        assertFalse(door.getMyUnlock());
        assertTrue(door.getAttempt());
    }

    @Test
    public void testGetOption1() {
        assertNotNull(door.getOption1());
    }

    @Test
    public void testGetMyOption2() {
        assertNotNull(door.getMyOption2());
    }

    @Test
    public void testGetMyOption3() {
        assertNotNull(door.getMyOption3());
    }

    @Test
    public void testGetMyOption4() {
        assertNotNull(door.getMyOption4());
    }

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
        door.setPlayerMyAnswer("player answer");
        assertEquals("player answer", door.getPlayerMyAnswer());
    }

    @Test
    public void testGetQAnswer() {
        assertNotNull(door.getQAnswer());
    }

}
