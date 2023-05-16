package Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
public class StoneTest {

    private Player myPlayer;
    @BeforeEach
    void setUp() {
        myPlayer = new Player();
    }

    @Test
    void testTimeStone() {
        TimeStone timeStone = new TimeStone();
        myPlayer.addToBackpack(timeStone);
        System.out.println(myPlayer.getTimeLimit());
        myPlayer.useStone(timeStone);
        assertEquals(20, myPlayer.getTimeLimit());
    }

    @Test
    void testPowerStone() {
        PowerStone powerStone = new PowerStone();
        myPlayer.addToBackpack(powerStone);
        System.out.println(myPlayer.getPlayerSpeed());
        myPlayer.useStone(powerStone);
        assertEquals(5, myPlayer.getPlayerSpeed());
    }

}