package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ChestTest {
    @Test
    void testFullChestToString() {
        MindStone mindStone = new MindStone();
        Chest mindChest = new Chest(mindStone);
        String actual = mindChest.chestToString();
        assertEquals("Mind Stone", actual);
    }

    @Test
    void testEmptyChestToString() {
        Chest emptyChest = new Chest();
        String actual = emptyChest.chestToString();
        assertEquals("Chest empty", actual);
    }

    @Test
    void testAddToChest() {
        MindStone mindStone = new MindStone();
        Chest chest = new Chest(mindStone);
        chest.addToChest(new TimeStone());
        String actual = chest.chestToString();
        assertEquals("Time Stone", actual);
    }

    @Test
    void testTakeStone() {
        Player player = new Player();
        Chest chest = new Chest(new PowerStone());
        player.takeStone(chest);
        player.displayBackpack();
        assertEquals(chest.isEmptyChest(),true);
        assertEquals("Chest empty", chest.chestToString());
    }

    @Test
    void testClearChest() {
        Chest chest = new Chest(new PowerStone());
        chest.clearChest();
        assertEquals(true, chest.isEmptyChest());
    }

    @Test
    void testGetMyStone() {
        Chest chest = new Chest(new SpaceStone());
        Stone stone = chest.getMyStone();
        assertEquals("Space Stone", stone.getStoneName());
    }

    @Test
    void testGetMyStoneEmptyChest() {
        Chest chest = new Chest();
        assertEquals(null, chest.getMyStone());
    }
    @Test
    void testRandomProbability() {
        Chest chest = new Chest();
        chest.setMyRandomNumber(120);
        chest.randomProbability();
        assertEquals(false, chest.isEmptyChest());
    }
}