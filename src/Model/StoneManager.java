package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
/**
 * StoneManager class keeps track of stones that have been used.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class StoneManager implements Serializable {
    /** The list of stones */
    private List<Stone> myStoneRecord;

    /**
     * Constructor initializes fields.
     */
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
        myStoneRecord.add(powerStone);
        myStoneRecord.add(mindStone);
        myStoneRecord.add(realityStone);
        myStoneRecord.add(soulStone);
        myStoneRecord.add(spaceStone);
        myStoneRecord.add(timeStone);
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

    /**
     * Getter for myStoneRecord.
     * @return myStoneRecord.
     */
    public List getMyStoneRecord() {
        return myStoneRecord;
    }

}
