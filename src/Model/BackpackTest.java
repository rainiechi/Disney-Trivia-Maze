package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
/**
 * BackpackTest tests methods in Backpack class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class BackpackTest {
    /** Create instance of Backpack class to use in the tests */
    private Backpack myBackpack;
    /** Create instance of Player class to use in the tests */
    private Player myPlayer;

    @BeforeEach
    void setUp() {
        myPlayer = new Player();
        myBackpack = new Backpack();
    }

    /**
     * Tests addToBackpack method
     */
    @Test
    void testAddToBackpack() {
        myBackpack.addToBackpack(new MindStone());
        assertEquals(1, myBackpack.getMyCurrItems());
    }
    /**
     * Tests addToBackpack method when all slots are filled
     */
    @Test
    void testAddToBackpackFull() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        myBackpack.addToBackpack(new TimeStone());
        myBackpack.addToBackpack(new SpaceStone());
        myBackpack.addToBackpack(new RealityStone());
        assertEquals(6, myBackpack.getMyCurrItems());
    }
    /**
     * Tests addToBackpack method when you add more than 6 stones
     */
    @Test
    void testAddToBackpackPastMax() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        myBackpack.addToBackpack(new TimeStone());
        myBackpack.addToBackpack(new SpaceStone());
        myBackpack.addToBackpack(new RealityStone());

        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> myBackpack.addToBackpack(new RealityStone()),
                "Expected addToBackpack() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("Game error, there should be no more than 6 stones in game"));
    }

    /**
     * Tests getStone method
     */
    @Test
    void testGetStone() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        assertEquals("Mind Stone", myBackpack.getStone(0).getStoneName());
        assertEquals("Soul Stone", myBackpack.getStone(1).getStoneName());
        assertEquals("Power Stone", myBackpack.getStone(2).getStoneName());
    }

    /**
     * Tests getStone method if there is nothing in backpack
     */
    @Test
    void testGetStoneNull() {
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> myBackpack.getStone(0),
                "Expected getStone() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("No stone at this index"));
    }

    /**
     * Test deleteStone method from backpack
     */
    @Test
    void testDeleteStone() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        System.out.println(myBackpack.getStone(1));
        myBackpack.deleteStone(1);
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> myBackpack.getStone(1),
                "Expected getStone() to throw, but it didn't"
        );
        assertTrue(thrown.getMessage().equals("No stone at this index"));
    }

    /**
     * Test findStone method from Backpack
     */
    @Test
    void testFindStone() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        myBackpack.addToBackpack(new TimeStone());
        myBackpack.addToBackpack(new SpaceStone());
        myBackpack.addToBackpack(new RealityStone());
        assertEquals(2, myBackpack.findStone(new PowerStone()));
    }

    /**
     * Tests findStone method if the specified stone isn't inside.
     */
    @Test
    void testFindStoneNull() {
        assertEquals(-1, myBackpack.findStone(new PowerStone()));
    }

    /**
     * Tests getCurrItems method which is the current number of items in backpack.
     */
    @Test
    void getCurrItems() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        myBackpack.addToBackpack(new TimeStone());
        myBackpack.addToBackpack(new SpaceStone());
        myBackpack.addToBackpack(new RealityStone());
        assertEquals(6, myBackpack.getMyCurrItems());
    }
}
