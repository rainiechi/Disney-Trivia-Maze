package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BackpackTest {
    /** Create instance of Player class to use in the tests */
    private Backpack myBackpack;

    @BeforeEach
    void setUp() {
        myBackpack = new Backpack();
    }
    @Test
    void testAddToBackpack() {
        myBackpack.addToBackpack(new MindStone());

    }
}
