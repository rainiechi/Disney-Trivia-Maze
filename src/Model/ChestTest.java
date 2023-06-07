package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * ChestTest tests methods in chest class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class ChestTest {
    /**
     * Tests stone in chest
     */
    @Test
    void testFullChestToString() {
        MindStone mindStone = new MindStone();
        Chest mindChest = new Chest(mindStone);
        String actual = mindChest.chestToString();
        assertEquals("Mind Stone", actual);
    }

    /**
     * Test empty chest
     */
    @Test
    void testEmptyChestToString() {
        Chest emptyChest = new Chest(new StoneManager());
        String actual = emptyChest.chestToString();
        assertEquals("Chest empty", actual);
    }

    /**
     * Test method addToChest
     */
    @Test
    void testAddToChest() {
        MindStone mindStone = new MindStone();
        Chest chest = new Chest(mindStone);
        chest.addToChest(new TimeStone());
        String actual = chest.chestToString();
        assertEquals("Time Stone", actual);
    }

    /**
     * Tests that the stone is deleted from chest when player takes stone.
     */
    @Test
    void testTakeStone() {
        Player player = new Player();
        Chest chest = new Chest(new PowerStone());
        player.takeStone(chest);
        player.displayBackpack();
        assertEquals(chest.isEmptyChest(),true);
        assertEquals("Chest empty", chest.chestToString());
    }

    /**
     * Tests method clearChest
     */
    @Test
    void testClearChest() {
        Chest chest = new Chest(new PowerStone());
        chest.clearChest();
        assertEquals(true, chest.isEmptyChest());
    }

    /**
     * Tests that chest returns correct stone
     */
    @Test
    void testGetMyStone() {
        Chest chest = new Chest(new SpaceStone());
        Stone stone = chest.getMyStone();
        assertEquals("Space Stone", stone.getStoneName());
    }

    /**
     * Tests that chest returns null if it is empty.
     */
    @Test
    void testGetMyStoneEmptyChest() {
        Chest chest = new Chest(new StoneManager());
        assertEquals(null, chest.getMyStone());
    }

    /**
     * Tests the random probability of stones spawning
     */
    @Test
    void testRandomProbability() {
        StoneManager sm = new StoneManager();
        Chest chest = new Chest(sm);
        chest.setMyRandomNumber(121);
        chest.randomProbability(sm);
        assertTrue(chest.isEmptyChest());
    }
}