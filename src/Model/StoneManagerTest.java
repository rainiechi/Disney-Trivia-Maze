package Model;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StoneManagerTest {
    @Test
    public void testGetMyStoneRecord() {
        StoneManager stoneManager = new StoneManager();
        List<Stone> stoneRecord = stoneManager.getMyStoneRecord();
        assertNotNull(stoneRecord);
        assertEquals(6, stoneRecord.size());
    }

    @Test
    public void testMyStoneRecordTime() {
        StoneManager stoneManager = new StoneManager();
        List<Stone> stoneRecord = stoneManager.getMyStoneRecord();
        boolean containsTimeStone = false;

        for (Stone stone : stoneRecord) {
            if (stone instanceof TimeStone) {
                containsTimeStone = true;
                break;
            }
        }
        assertTrue(containsTimeStone);
    }

    @Test
    public void testMyStoneRecordPower() {
        StoneManager stoneManager = new StoneManager();
        List<Stone> stoneRecord = stoneManager.getMyStoneRecord();
        boolean containsPowerStone = false;

        for (Stone stone : stoneRecord) {
            if (stone instanceof PowerStone) {
                containsPowerStone = true;
                break;
            }
        }
        assertTrue(containsPowerStone);
    }

    @Test
    public void testMyStoneRecordSoul() {
        StoneManager stoneManager = new StoneManager();
        List<Stone> stoneRecord = stoneManager.getMyStoneRecord();
        boolean containsSoulStone = false;

        for (Stone stone : stoneRecord) {
            if (stone instanceof SoulStone) {
                containsSoulStone = true;
                break;
            }
        }
        assertTrue(containsSoulStone);
    }

    @Test
    public void testMyStoneRecordMind() {
        StoneManager stoneManager = new StoneManager();
        List<Stone> stoneRecord = stoneManager.getMyStoneRecord();
        boolean containsMindStone = false;

        for (Stone stone : stoneRecord) {
            if (stone instanceof MindStone) {
                containsMindStone = true;
                break;
            }
        }

        assertTrue(containsMindStone);
    }

    @Test
    public void testMyStoneRecordSpace() {
        StoneManager stoneManager = new StoneManager();
        List<Stone> stoneRecord = stoneManager.getMyStoneRecord();
        boolean containsSpaceStone = false;

        for (Stone stone : stoneRecord) {
            if (stone instanceof SpaceStone) {
                containsSpaceStone = true;
                break;
            }
        }

        assertTrue(containsSpaceStone);
    }

    @Test
    public void testMyStoneRecordReality() {
        StoneManager stoneManager = new StoneManager();
        List<Stone> stoneRecord = stoneManager.getMyStoneRecord();
        boolean containsRealityStone = false;

        for (Stone stone : stoneRecord) {
            if (stone instanceof RealityStone) {
                containsRealityStone = true;
                break;
            }
        }

        assertTrue(containsRealityStone);
    }



}