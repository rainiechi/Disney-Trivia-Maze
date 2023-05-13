package Model;

import Model.Player;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PlayerTest {
    /** Create instance of Player class to use in the tests */
    private Player myPlayer;

    @BeforeEach
    void setUp() {
        myPlayer = new Player(100, 100);
    }

    @Test
    void testPlayerSpeed() {
        myPlayer.setPlayerSpeed(5);
        assertEquals(5, myPlayer.getPlayerSpeed());
    }
    @Test
    void testPlayerSetSpeed() {
        myPlayer.setPlayerSpeed(10);
        assertEquals(10, myPlayer.getPlayerSpeed());
    }
    @Test
    void testPlayerX() {
        assertEquals(100, myPlayer.getX());
    }
    @Test
    void testPlayerSetX() {
        myPlayer.setX(150);
        assertEquals(150, myPlayer.getX());
    }
    @Test
    void testPlayerSetXException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> myPlayer.setX(-150),
                "Expected setX() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("the X coordinate must not be a negative number: -150"));
    }


    @Test
    void testPlayerY() {
        assertEquals(100, myPlayer.getY());
    }
    @Test
    void testPlayerSetY() {
        myPlayer.setY(150);
        assertEquals(150, myPlayer.getY());
    }
    @Test
    void testPlayerSetYException() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> myPlayer.setY(-150),
                "Expected setY() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("the Y coordinate must not be a negative number: -150"));
    }
    @Test
    void testPlayerHasSpaceStone() {
        myPlayer.setSpaceStone(true);
        assertEquals(true, myPlayer.hasSpaceStone());
    }
    @Test
    void testPlayerHasSoulStone() {
        myPlayer.setSoulStone(true);
        assertEquals(true, myPlayer.hasSoulStone());
    }
    @Test
    void testPlayerGetTimeLimit() {
        assertEquals(15, myPlayer.getTimeLimit());
    }
    @Test
    void testPlayerSetTimeLimit() {
        myPlayer.setTimeLimit(20);
        assertEquals(20, myPlayer.getTimeLimit());
    }

    @Test
    void testTakeSoulStone() {
        SoulStone soulStone = new SoulStone();
        Chest chest = new Chest(soulStone);
        myPlayer.takeStone(chest);
        myPlayer.displayBackpack(); //should have soul stone
        assertEquals(true, myPlayer.hasSoulStone());
        myPlayer.useStone(soulStone);
        myPlayer.displayBackpack(); //should have nothing
        assertEquals(false, myPlayer.hasSoulStone());
    }

    @Test
    void testTakeStoneFromEmptyChest() {
        Chest chest = new Chest();
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> myPlayer.takeStone(chest),
                "Expected takeStone() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().equals("Chest is empty"));
    }


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
    @Test
    void testUseStone() {
        MindStone mindstone = new MindStone();
        myPlayer.addToBackpack(mindstone);
        myPlayer.addToBackpack(new SoulStone());
        myPlayer.useStone(mindstone);
        assertEquals(2, myPlayer.getCurrItem());
    }

    @Test
    void testUseStone2() {
        TimeStone timestone = new TimeStone();
        myPlayer.addToBackpack(new RealityStone());
        myPlayer.addToBackpack(timestone);
        myPlayer.useStone(timestone);
        assertEquals(1, myPlayer.getCurrItem());
    }

}
