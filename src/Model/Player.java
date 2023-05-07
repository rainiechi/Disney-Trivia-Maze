package Model;

public class Player {
    private Backpack myBackpack;
    private int myX;
    private int myY;

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

}
