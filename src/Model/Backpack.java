package Model;

public class Backpack {
    private static final int MAX_ITEMS = 6;
    private Stone[] myStorage;
    private int currItems;

    /**
     * Backpack constructor
     */
    public Backpack() {
        myStorage = new Stone[MAX_ITEMS]; //at most stores all 6 stones
        currItems = 0;
    }

    /**
     * Adds Stone to the backpack, adds to the first open slot.
     * @param theStone the stone to be added
     */
    public void addToBackpack(final Stone theStone) {
        if (currItems == MAX_ITEMS) {
            throw new IllegalArgumentException("Game error, there should be no more than 6 stones in game"); //for debug purposes
        }
        for (int i = 0; i < MAX_ITEMS; i++) { //adds to first empty slot
            if (myStorage[i] == null) {
                myStorage[i] = theStone;
                currItems++;
                break;
            }
        }
    }

    /**
     * Uses the specified stone then deletes it from backpack;
     * @param theStone theStone to be used.
     */
    public void useStone(final Stone theStone) {
        int stoneIndex = findStone(theStone);
        if (stoneIndex < 0) {
            // Changed to exceptions for testing.
            throw new IllegalArgumentException("Player does not have this stone.");
        } else {
            Stone stone = myStorage[stoneIndex];
            stone.useAbility();
            if (stone.getUses() == 0) {
                deleteStone(stoneIndex);
            }
        }
    }

    /**
     * Delete the stone at the specified index.
     * @param theIndex the specified index to delete
     */
    public void deleteStone(final int theIndex) {
        myStorage[theIndex] = null;
        currItems--;
    }

    /**
     * Finds if specified stone is in the backpack.
     * @param theStone the stone to be found
     * @return the stone's index, returns -1 if stone not found
     */
    public int findStone(final Stone theStone) {
        String stoneType = theStone.getStoneName();
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (stoneType.equals(myStorage[i].getStoneName())) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Prints out current content of backpack.
     */
    public void displayCurrInventory() {
        System.out.println("Current inventory:");
        for (int i = 0; i < MAX_ITEMS; i++) {
            if (myStorage[i] != null) {
                System.out.println(myStorage[i]);
            }
        }
    }

    public int getCurrItems() {
        return currItems;
    }

}
