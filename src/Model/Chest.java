package Model;

import java.awt.image.BufferedImage;

public class Chest {
    private transient BufferedImage myImage;
    private Stone myStone; //the stone in the chest
    private boolean myEmptyChest; //if the chest is currently empty

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
    }

    /**
     * Adds the specified stone to the chest
     * @param theStone the Stone to be added
     */
    public void addToChest(Stone theStone) {
        myStone = theStone;
        myEmptyChest = false;
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
    public BufferedImage getImage() {
        return myImage;
    }


}
