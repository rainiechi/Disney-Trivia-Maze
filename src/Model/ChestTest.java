package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class ChestTest {

    private Chest myChest;


    @Test
    void testAddToChest() {
        MindStone mindStone = new MindStone();
        Chest mindChest = new Chest(mindStone);
        String expected = mindChest.displayChest();
        assertEquals(expected, "Mind Stone");
    }

    @Test
    void testEmptyChest() {
        Chest emptyChest = new Chest();
        String expected = emptyChest.displayChest();
        assertEquals(expected, "Chest empty");
    }
}