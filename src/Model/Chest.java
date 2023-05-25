package Model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

public class Chest {
    private Stone myStone; //the stone in the chest
    private boolean myEmptyChest; //if the chest is currently empty
    private int myRandomNumber;
    private int myStoneIndex;
    private Random myRand;

    /**
     * Constructor for non-empty chest.
     * @param theStone the stone in the chest
     */
    public Chest(final Stone theStone) {
        myStone = theStone;
        myEmptyChest = false;
    }

    /**
     * Constructor for empty chest.
     */
    public Chest() {
        myStone = null;
        myEmptyChest = true;
        myRand = new Random();
        myRandomNumber = myRand.nextInt(122);
        randomProbability();
    }

    /**
     * Adds the specified stone to the chest
     * @param theStone the Stone to be added
     */
    public void addToChest(Stone theStone) {
        myStone = theStone;
        myEmptyChest = false;
    }
    public void randomProbability() {
        Stone stone = null;
        ArrayList<Stone> stones = new ArrayList<>();
        StoneFactory sf = new StoneFactory();
        stones.add(sf.createStone("Time"));
        stones.add(sf.createStone("Power"));
        stones.add(sf.createStone("Mind"));
        stones.add(sf.createStone("Soul"));
        stones.add(sf.createStone("Space"));
        stones.add(sf.createStone("Reality"));
        // 40% Chance probability of chest containing stones

        if (myRandomNumber % 3 == 0) {
            // Chooses random stone from arraylist
            myStoneIndex =  myRand.nextInt(6);
            stone = stones.get(myStoneIndex);
            // removes from list so there are no duplicate types
            stones.remove(myStoneIndex);
        }
        addToChest(stone);
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
    public boolean getMyEmptyChest() {
        return myEmptyChest;
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
    public void setMyStoneIndex(int theIndex) {
        myStoneIndex = theIndex;
    }


}
