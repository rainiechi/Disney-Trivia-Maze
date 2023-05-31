package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoneManager implements Serializable {

    private List<Stone> myStoneRecord;

    public StoneManager() {
        myStoneRecord = new ArrayList<>(6);
        initializeStone();
    }

    /**
     * sets up the 6 stones in the record.
     */
    private void initializeStone() {
        StoneFactory stoneFactory = new StoneFactory();
        Stone soulStone = stoneFactory.createStone("soul");
        Stone timeStone = stoneFactory.createStone("time");
        Stone powerStone = stoneFactory.createStone("power");
        Stone realityStone = stoneFactory.createStone("reality");
        Stone spaceStone = stoneFactory.createStone("space");
        Stone mindStone = stoneFactory.createStone("mind");
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
        Stone result = null;
        if (myStoneRecord.size() != 0) {
            Random random = new Random();
            int randomIndex = random.nextInt(myStoneRecord.size());
            result = myStoneRecord.get(randomIndex);
            myStoneRecord.remove(randomIndex);
        }
        return result;
    }

}
