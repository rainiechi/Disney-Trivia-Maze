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
        myBackpack = new Backpack(myPlayer);
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
    void testUseStonePlayerDoesNotHave() {
        myBackpack.addToBackpack(new MindStone());
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () -> myBackpack.useStone(new RealityStone()),
                "Expected useStone() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().equals("Player does not have this stone."));
    }
    @Test
    void testUseStone() {
        MindStone mindstone = new MindStone();
        myBackpack.addToBackpack(mindstone);
        myBackpack.addToBackpack(new SoulStone());
        myBackpack.useStone(mindstone);
        assertEquals(2, myBackpack.getCurrItems());
    }
    @Test
    void testUseStone2() {
        TimeStone timestone = new TimeStone();
        myBackpack.addToBackpack(new RealityStone());
        myBackpack.addToBackpack(timestone);
        myBackpack.useStone(timestone);
        assertEquals(1, myBackpack.getCurrItems());
    }
}
