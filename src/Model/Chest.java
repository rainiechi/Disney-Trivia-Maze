package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
/**
 * Chest class represents chest object in game.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class Chest {
    /** The stone in chest */
    private Stone myStone;
    /** Random number */
    private int myRandomNumber;
    /** Random object */
    private Random myRand;
    /** Boolean for locked chest */
    private boolean myLocked;

    /**
     * Constructor for non-empty chest.
     * @param theStone the stone in the chest
     */
    public Chest(final Stone theStone) {
        if (theStone == null) {
            throw new IllegalArgumentException("Stone cannot be null");
        }
        myStone = theStone;
        myLocked = false;
    }

    /**
     * Constructor for empty chest.
     */
    public Chest(final StoneManager theStoneM) {
        if (theStoneM == null) {
            throw new IllegalArgumentException("Stone Manager cannot be null");
        }
        myStone = null;
        myRand = new Random();
        myRandomNumber = myRand.nextInt(122);
        myLocked = false;
        randomProbability(theStoneM);
    }

    /**
     * Adds the specified stone to the chest
     * @param theStone the Stone to be added
     */
    public void addToChest(final Stone theStone) {
        myStone = theStone;
    }
    public void randomProbability(final StoneManager theStoneM) {
        if (theStoneM == null) {
            throw new IllegalArgumentException("Stone Manager cannot be null");
        }
        Stone result = null;
        // 40% Chance probability of chest containing stones
        if (myRandomNumber % 3 == 0) {
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
        // used for testing purposes
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
    }

    /**
     * Setter method sets the randomized number.
     * @param theNum number to set
     */
    public void setMyRandomNumber(final int theNum) {
        myRandomNumber = theNum;
    }

    /**
     * Checks if the chest is locked.
     * @return true if locked, otherwise false.
     */
    public boolean isLocked() {
        return myLocked;
    }

    /**
     * Setter method sets the chest lock.
     * @param theLock boolean to set.
     */
    public void setLocked(final boolean theLock) {
        myLocked = theLock;
    }

}
