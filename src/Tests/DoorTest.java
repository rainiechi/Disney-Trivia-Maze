package Tests;

import Model.Door;
import Model.Player;
import SQLite.DBRetriever;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DoorTest {

    private Door door;
    private Player player;

    @Before
    public void setUp() {
        door = new Door();
        player = new Player(0,0);
    }

    @Test
    public void testLockedDoorWithSoulStone() {
        player.setSoulStone(true);
        door.setAttempted(true);
        door.lockedDoor(player);
        Assert.assertEquals("Would you like to use the Soul Stone to attempt this door again?", System.out.toString().trim());
    }

    @Test
    public void testLockedDoorWithoutSoulStone() {
        player.setSoulStone(false);
        door.setAttempted(true);
        door.lockedDoor(player);
        Assert.assertEquals("Door has been attempted.", System.out.toString().trim());
    }

    @Test
    public void testCheckPlayerAnswerCorrect() {
        door.setPlayerMyAnswer("answer");
        door.checkPlayerAnswer();
        Assert.assertTrue(door.getMyUnlock());
        Assert.assertTrue(door.getAttempt());
    }

    @Test
    public void testCheckPlayerAnswerIncorrect() {
        door.setPlayerMyAnswer("wrong answer");
        door.checkPlayerAnswer();
        Assert.assertFalse(door.getMyUnlock());
        Assert.assertTrue(door.getAttempt());
    }

    @Test
    public void testGetOption1() {
        Assert.assertNotNull(door.getOption1());
    }

    @Test
    public void testGetMyOption2() {
        Assert.assertNotNull(door.getMyOption2());
    }

    @Test
    public void testGetMyOption3() {
        Assert.assertNotNull(door.getMyOption3());
    }

    @Test
    public void testGetMyOption4() {
        Assert.assertNotNull(door.getMyOption4());
    }

    @Test
    public void testSetMyUnlock() {
        door.setMyUnlock(true);
        Assert.assertTrue(door.getMyUnlock());
    }

    @Test
    public void testSetAttempted() {
        door.setAttempted(true);
        Assert.assertTrue(door.getAttempt());
    }

    @Test
    public void testSetPlayerMyAnswer() {
        door.setPlayerMyAnswer("player answer");
        Assert.assertEquals("player answer", door.getPlayerMyAnswer());
    }

    @Test
    public void testGetQAnswer() {
        Assert.assertNotNull(door.getQAnswer());
    }

}
