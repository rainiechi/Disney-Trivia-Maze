package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * StoneTests tests methods in Stone class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class StoneTest {
    /** Player object to test */
    private Player myPlayer;

    /**
     * Method initializes fields to test.
     */
    @BeforeEach
    void setUp() {
        myPlayer = new Player();
    }

    /**
     * Method tests time stone was used and functions properly.
     */
    @Test
    void testTimeStone() {
        TimeStone timeStone = new TimeStone();
        myPlayer.addToBackpack(timeStone);
        System.out.println(myPlayer.getTimeLimit());
        myPlayer.useStone(timeStone);
        assertEquals(20, myPlayer.getTimeLimit());
    }
    /**
     * Method tests power stone was used and functions properly.
     */
    @Test
    void testPowerStone() {
        PowerStone powerStone = new PowerStone();
        myPlayer.addToBackpack(powerStone);
        System.out.println(myPlayer.getPlayerSpeed());
        myPlayer.useStone(powerStone);
        assertEquals(5, myPlayer.getPlayerSpeed());
    }

}