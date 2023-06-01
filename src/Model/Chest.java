package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Chest implements Serializable {
    private Stone myStone; //the stone in the chest
    private boolean myEmptyChest; //if the chest is currently empty
    private int myRandomNumber;
    private Random myRand;
    private boolean myLocked;

    /**
     * Constructor for non-empty chest.
     * @param theStone the stone in the chest
     */
    public Chest(final Stone theStone) {
        myStone = theStone;
        myEmptyChest = false;
        myLocked = false;
    }

    /**
     * Constructor for empty chest.
     */
    public Chest(StoneManager theStoneM) {
        myStone = null;
        myEmptyChest = true;
        myRand = new Random();
        myRandomNumber = myRand.nextInt(16);
        myLocked = false;
        randomProbability(theStoneM);
    }

    /**
     * Adds the specified stone to the chest
     * @param theStone the Stone to be added
     */
    public void addToChest(Stone theStone) {
        myStone = theStone;
    }
    public void randomProbability(StoneManager theStoneM) {
        Stone result = null;
        // 40% Chance probability of chest containing stones
        if (myRandomNumber >= 0) {
            // Chooses random stone from arraylist
            result = theStoneM.generateStone();
        }
        addToChest(result);
    }

    /**
     * Return the name of the stone inside.
     * @return name of the stone inside.
     */
    public String chestToString() {
        if (myStone != null) {
            return myStone.getStoneName();
        } else {
            return "Chest empty";
        }
    }

    /**
     * Returns if the chest is empty.
     * @return if the chest is empty
     */
    public boolean isEmptyChest() {
        boolean result = true;
        if (myStone != null) {
            result = false;
        }
        return result;
    }

    /**
     * Returns the stone in the chest.
     * @return the stone in the chest
     */
    public Stone getMyStone() {
        return myStone;
    }

    /**
     * Clears the chest.
     */
    public void clearChest() {
        myStone = null;
        myEmptyChest = true;
    }
    public void setMyRandomNumber(int theNum) {
        myRandomNumber = theNum;
    }

    public boolean isLocked() {
        return myLocked;
    }
    public void setLocked(boolean theLock) {
        myLocked = theLock;
    }

}
