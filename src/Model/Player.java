package Model;

public class Player {
    private Backpack myBackpack;
    private int myX;
    private int myY;
    private int myPlayerSpeed;

    private boolean mySpaceStone;
    private boolean mySoulStone;

    public Player() {
        myPlayerSpeed = 3;
        mySpaceStone = false;
        mySoulStone = false;
    }

    /**
     * Player constructor.
     * @param theX the x coordinate
     * @param theY the y coordinate
     */
    public Player(final int theX, final int theY) {
        if (theX < 0 || theY < 0) {
            throw new IllegalArgumentException("the X, Y coordinates must not "
                    + "be negative numbers: " + theX + ", " + theY);
        }
        myBackpack = new Backpack();
        myX = theX;
        myY = theY;
        myPlayerSpeed = 3;
        mySpaceStone = false;
        mySoulStone = false;
    }

    /**
     * Prints out player's current coordinates and backpack content.
     */
    public void displayPlayerStatus() {
        System.out.println("Player's current coordinate: " + myX + ", " + myY);
        myBackpack.displayCurrInventory();
    }

    /**
     * Sets player's X coordinate
     * @param theX the X coordinate to be set
     */
    public void setX(final int theX) {
        if (theX < 0) {
            throw new IllegalArgumentException("the X"
                    + " coordinate must not be a negative number: " + theX);
        }
        myX = theX;
    }

    /**
     * Sets player's Y coordinate
     * @param theY the Y coordinate to be set
     */
    public void setY(final int theY) {
        if (theY < 0) {
            throw new IllegalArgumentException("the Y coordinate must not be "
                    + "a negative number: " + theY);
        }
        myY = theY;
    }

    /**
     * Gets the player's current X coordinate.
     * @return current X coordinate
     */
    public int getX() {
        return myX;
    }

    /**
     * Gets the player's current Y coordinate.
     * @return current Y coordinate
     */
    public int getY() {
        return myY;
    }

    /**
     * Gets the player's current speed.
     * @return player speed.
     */
    public int getPlayerSpeed() { return myPlayerSpeed;}

    /**
     * Sets the player's speed.
     * @param speed speed to set.
     */
    public void setPlayerSpeed(final int speed) {
        myPlayerSpeed = speed;
    }


    // Will be used to later on to allow player to bypass door without trivia.

    /**
     * Checks if player has a space stone.
     * @return true if player has a space stone, otherwise false.
     */
    public boolean hasSpaceStone() { return mySpaceStone;}

    /**
     * Sets space stone to true or false.
     * @param check boolean to be passed in.
     */
    public void setSpaceStone(boolean check) {
        mySpaceStone = check;
    }

    // Will be used later to allow player to reset door. Field and method may be moved to DOOR class
    // once created.
    /**
     * Checks if player has a soul stone.
     * @return true if player has a soul stone, otherwise false.
     */
    public boolean hasSoulStone() { return mySoulStone;}

    /**
     * Sets space stone to true or false.
     * @param check boolean to be passed in.
     */
    public void setSoulStone(boolean check) {
        mySoulStone = check;
    }
}
