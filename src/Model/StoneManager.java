package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoneManager {
    private List<Stone> myStoneRecord;

    public StoneManager() {
        myStoneRecord = new ArrayList<>(6);
        initializeStone();
    }

    /**
     * sets up the 6 stones in the record.
     */
    private void initializeStone() {
        SoulStone soulStone = new SoulStone();
        TimeStone timeStone = new TimeStone();
        PowerStone powerStone = new PowerStone();
        RealityStone realityStone = new RealityStone();
        SpaceStone spaceStone = new SpaceStone();
        MindStone mindStone = new MindStone();
        myStoneRecord.add(soulStone);
        myStoneRecord.add(timeStone);
        myStoneRecord.add(powerStone);
        myStoneRecord.add(realityStone);
        myStoneRecord.add(spaceStone);
        myStoneRecord.add(mindStone);
    }

    /**
     * Randomly generates 1 stone then remove said stone from the record.
     * @return the randomly pulled stone
     */
    public Stone generateStone() {
        if (myStoneRecord.size() == 0) {
            throw new IllegalArgumentException("all 6 stones have been generated");
        }
        Random random = new Random();
        int randomIndex = random.nextInt(myStoneRecord.size());
        Stone result = myStoneRecord.get(randomIndex);
        myStoneRecord.remove(randomIndex);
        return result;
    }

}
