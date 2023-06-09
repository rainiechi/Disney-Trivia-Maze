package Model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class StoneFactoryTest {

    @Test
    public void testCreateStone_TimeStone() {
        StoneFactory stoneFactory = new StoneFactory();
        Stone stone = stoneFactory.createStone("Time");

        assertNotNull(stone);
        assertTrue(stone instanceof TimeStone);
    }

    @Test
    public void testCreateStone_PowerStone() {
        StoneFactory stoneFactory = new StoneFactory();
        Stone stone = stoneFactory.createStone("Power");

        assertNotNull(stone);
        assertTrue(stone instanceof PowerStone);
    }

    @Test
    public void testCreateStone_SpaceStone() {
        StoneFactory stoneFactory = new StoneFactory();
        Stone stone = stoneFactory.createStone("Space");

        assertNotNull(stone);
        assertTrue(stone instanceof SpaceStone);
    }

    @Test
    public void testCreateStone_SoulStone() {
        StoneFactory stoneFactory = new StoneFactory();
        Stone stone = stoneFactory.createStone("Soul");

        assertNotNull(stone);
        assertTrue(stone instanceof SoulStone);
    }

    @Test
    public void testCreateStone_RealityStone() {
        StoneFactory stoneFactory = new StoneFactory();
        Stone stone = stoneFactory.createStone("Reality");

        assertNotNull(stone);
        assertTrue(stone instanceof RealityStone);
    }

    @Test
    public void testCreateStone_MindStone() {
        StoneFactory stoneFactory = new StoneFactory();
        Stone stone = stoneFactory.createStone("Mind");

        assertNotNull(stone);
        assertTrue(stone instanceof MindStone);
    }

    @Test
    public void testCreateStone_InvalidStoneType() {
        StoneFactory stoneFactory = new StoneFactory();

        assertThrows(IllegalArgumentException.class, () -> {
            stoneFactory.createStone("Invalid");
        });
    }


}