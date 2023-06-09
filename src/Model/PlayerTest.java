package Model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * PlayerTest tests methods in Player class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class PlayerTest {
    /** Create instance of Player class to use in the tests */
    private Player myPlayer;

    /**
     * Method initializes fields needed for tests.
     */
    @BeforeEach
    void setUp() {
        myPlayer = new Player();
    }

    /**
     * Test setPlayerSpeed method
     */
    @Test
    void testPlayerSpeed() {
        myPlayer.setPlayerSpeed(5);
        assertEquals(5, myPlayer.getPlayerSpeed());
    }
    /**
     * Test setPlayerSpeed method
     */
    @Test
    void testPlayerSetSpeed() {
        myPlayer.setPlayerSpeed(10);
        assertEquals(10, myPlayer.getPlayerSpeed());
    }
    /**
     * Test getter method for screen X
     */
    @Test
    void testPlayerScreenX() {
        assertEquals(0, myPlayer.getScreenX());
    }

    /**
     * Tests setter method for screen X
     */
    @Test
    void testPlayerSetScreenX() {
        myPlayer.setScreenX(150);
        assertEquals(150, myPlayer.getScreenX());
    }

    /**
     * Tests setter method for screen X if coordinate is out of bounds.
     */
    @Test
    void testPlayerSetXException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> myPlayer.setScreenX(-150),
                "Expected setX() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("the X coordinate must not be a negative number: -150"));
    }

    /**
     * Tests getter method for screen Y
     */
    @Test
    void testPlayerY() {
        assertEquals(0, myPlayer.getScreenY());
    }
    /**
     * Tests setter method for screen Y
     */
    @Test
    void testPlayerSetY() {
        myPlayer.setScreenY(150);
        assertEquals(150, myPlayer.getScreenY());
    }
    /**
     * Tests setter method for screen Y if coordinate is out of bounds.
     */
    @Test
    void testPlayerSetYException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> myPlayer.setScreenY(-150),
                "Expected setY() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("the Y coordinate must not be a negative number: -150"));
    }

    /**
     * Tests both setter and getter method for Space stone.
     */
    @Test
    void testPlayerHasSpaceStone() {
        myPlayer.setSpaceStone(true);
        assertTrue(myPlayer.isSpaceStone());
    }

    /**
     * Tests both setter and getter method for Soul stone
     */
    @Test
    void testPlayerHasSoulStone() {
        myPlayer.setSoulStone(true);
        assertTrue(myPlayer.isSoulStone());
    }

    /**
     * Tests getter method for time limit.
     */
    @Test
    void testPlayerGetTimeLimit() {
        assertEquals(15, myPlayer.getTimeLimit());
    }
    /**
     * Tests setter method for time limit.
     */
    @Test
    void testPlayerSetTimeLimit() {
        myPlayer.setTimeLimit(20);
        assertEquals(20, myPlayer.getTimeLimit());
    }

    /**
     * Tests method takeStone if chest has soul stone. Also
     * tests for useStone method.
     */
    @Test
    void testTakeSoulStone() {
        SoulStone soulStone = new SoulStone();
        Chest chest = new Chest(soulStone);
        myPlayer.takeStone(chest);
        myPlayer.displayBackpack(); //should have soul stone
        assertEquals(true, myPlayer.isSoulStone());
        myPlayer.useStone(soulStone);
        myPlayer.displayBackpack(); //should have nothing
        assertEquals(false, myPlayer.isSoulStone());
    }

    /**
     * Tests method takeStone if chest is empty
     */
    @Test
    void testTakeStoneFromEmptyChest() {
        Chest chest = new Chest(new StoneManager());
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> myPlayer.takeStone(chest),
                "Expected takeStone() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().equals("Chest is empty"));
    }

    /**
     * Tests method useStone that player does not have.
     */
    @Test
    void testUseStonePlayerDoesNotHave() {
        myPlayer.addToBackpack(new MindStone());
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> myPlayer.useStone(new RealityStone()),
                "Expected useStone() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().equals("Player does not have this stone."));
    }

    /**
     * Tests method useStone with stones that have multiple uses.
     */
    @Test
    void testUseStoneWithMultipleUses() {
        MindStone mindstone = new MindStone();
        myPlayer.addToBackpack(mindstone);
        myPlayer.addToBackpack(new SoulStone());
        // Mind stone can be used 5 times
        myPlayer.useStone(mindstone);
        assertEquals(2, myPlayer.getCurrItem());
    }

    /**
     * Tests method useStone with stones that have 1 time uses.
     */
    @Test
    void testUseStoneWithOneTimeUse() {
        TimeStone timestone = new TimeStone();
        myPlayer.addToBackpack(new RealityStone());
        myPlayer.addToBackpack(timestone);
        myPlayer.useStone(timestone);
        assertEquals(1, myPlayer.getCurrItem());
    }

}
