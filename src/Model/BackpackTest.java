package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class BackpackTest {
    /** Create instance of Player class to use in the tests */
    private Backpack myBackpack;
    private Player myPlayer;

    @BeforeEach
    void setUp() {
        myPlayer = new Player(1,1);
        myBackpack = new Backpack();
    }
    @Test
    void testAddToBackpack() {
        myBackpack.addToBackpack(new MindStone());
        assertEquals(1, myBackpack.getCurrItems());
    }
    @Test
    void testAddToBackpackFull() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        myBackpack.addToBackpack(new TimeStone());
        myBackpack.addToBackpack(new SpaceStone());
        myBackpack.addToBackpack(new RealityStone());
        assertEquals(6, myBackpack.getCurrItems());
    }

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

    @Test
    void testGetStone() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        assertEquals("Mind Stone", myBackpack.getStone(0).getStoneName());
        assertEquals("Soul Stone", myBackpack.getStone(1).getStoneName());
        assertEquals("Power Stone", myBackpack.getStone(2).getStoneName());
    }

    @Test
    void testGetStoneNull() {
        NullPointerException thrown = assertThrows(
                NullPointerException.class,
                () -> myBackpack.getStone(0),
                "Expected getStone() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("No stone at this index"));
    }

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

    @Test
    void testFindStoneNull() {
        assertEquals(-1, myBackpack.findStone(new PowerStone()));
    }
    @Test
    void getCurrItems() {
        myBackpack.addToBackpack(new MindStone());
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.addToBackpack(new PowerStone());
        myBackpack.addToBackpack(new TimeStone());
        myBackpack.addToBackpack(new SpaceStone());
        myBackpack.addToBackpack(new RealityStone());
        assertEquals(6, myBackpack.getCurrItems());
    }
}
