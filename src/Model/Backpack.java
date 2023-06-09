package Model;

import java.io.Serializable;

/**
 * Backpack class represents the player's inventory.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class Backpack implements Serializable {
    /** Max items in backpack */
    private static final int MAX_ITEMS = 6;
    /** Array of Stone objects */
    private Stone[] myStorage;
    /** Current number of items in backpack */
    private int myCurrItems;
    /**
     * Backpack constructor
     */
    public Backpack() {
        myStorage = new Stone[MAX_ITEMS]; //at most stores all 6 stones
        myCurrItems = 0;
    }

    /**
     * Adds Stone to the backpack, adds to the first open slot.
     * @param theStone the stone to be added
     */
    public void addToBackpack(final Stone theStone) {
        if (theStone == null) {
            throw new IllegalArgumentException("Stone cannot be null");
        }
        if (myCurrItems == MAX_ITEMS) {
            throw new IllegalArgumentException("Game error, there should be no more than 6 stones in game"); //for debug purposes
        }
        for (int i = 0; i < MAX_ITEMS; i++) { //adds to first empty slot
            if (myStorage[i] == null) {
                myStorage[i] = theStone;
                myCurrItems++;
                break;
            }
        }
    }


    /**
     * Return the stone at the specified index slot
     * @param theIndex the index
     * @return the stone at the index
     */
    public Stone getStone(final int theIndex) {
        if (theIndex > 5 || theIndex < 0) {
            throw new IllegalArgumentException("Index must be 0~5");
        }
        return myStorage[theIndex];
    }

    /**
     * Delete the stone at the specified index.
     * @param theIndex the specified index to delete
     */
    public void deleteStone(final int theIndex) {
        if (theIndex > 5 || theIndex < 0) {
            throw new IllegalArgumentException("Index must be 0~5");
        }
        myStorage[theIndex] = null;
        myCurrItems--;
    }

    /**
     * Finds if specified stone is in the backpack.
     * @param theStone the stone to be found
     * @return the stone's index, returns -1 if stone not found
     */
    public int findStone(final Stone theStone) {
        if (theStone == null) {
            throw new IllegalArgumentException("Stone cannot be null");
        }
        String stoneType = theStone.getStoneName();
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (myStorage[i] != null && stoneType.equals(myStorage[i].getStoneName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints out current content of backpack.
     */
    public void displayCurrInventory() {
        // Used for debugging
        System.out.println("Current inventory:");
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (myStorage[i] != null) {
                System.out.println(myStorage[i].getStoneName());
            }
        }
    }

    /**
     * Returns current number of stones in backpack.
     * @return current number of stones in backpack
     */
    public int getMyCurrItems() {
        return myCurrItems;
    }

}