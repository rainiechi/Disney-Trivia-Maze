package Model;

import Model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
    void testPlayerY() {
        assertEquals(100, myPlayer.getY());
    }
    @Test
    void testPlayerSetY() {
        myPlayer.setY(150);
        assertEquals(150, myPlayer.getY());
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



}
