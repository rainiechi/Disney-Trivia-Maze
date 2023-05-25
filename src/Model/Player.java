package Model;

import java.io.Serializable;

public class Player implements Serializable {

    private Backpack myBackpack;
    private int myScreenX;
    private int myScreenY;

    private int myWorldX;
    private int myWorldY;
    private int myPlayerSpeed;

    private int myTimeLimit;

    private boolean mySpaceStone;
    private boolean mySoulStone;
    private transient GameSettings myGs;

    /**
     * Player constructor.
     */
    public Player() {
        myGs = new GameSettings();
        myBackpack = new Backpack();


        myScreenX = GameSettings.SCREEN_WIDTH/2 - (GameSettings.TILE_SIZE/2);
        myScreenY = GameSettings.SCREEN_HEIGHT/2 - (GameSettings.TILE_SIZE/2);
        myWorldX = GameSettings.TILE_SIZE * 35;
        myWorldY = GameSettings.TILE_SIZE * 70;
        myPlayerSpeed = 3;
        mySpaceStone = false;
        mySoulStone = false;
        myTimeLimit = 15;
    }


    /**
     * Sets player's X coordinate
     * @param theX the X coordinate to be set
     */
    public void setScreenX(final int theX) {
        if (theX < 0) {
            throw new IllegalArgumentException("the X"
                    + " coordinate must not be a negative number: " + theX);
        }
        myScreenX = theX;
    }

    /**
     * Sets player's Y coordinate
     * @param theY the Y coordinate to be set
     */
    public void setScreenY(final int theY) {
        if (theY < 0) {
            throw new IllegalArgumentException("the Y coordinate must not be "
                    + "a negative number: " + theY);
        }
        myScreenY = theY;
    }

    /**
     * Gets the player's current X coordinate.
     * @return current X coordinate
     */
    public int getScreenX() {
        return myScreenX;
    }

    /**
     * Gets the player's current Y coordinate.
     * @return current Y coordinate
     */
    public int getScreenY() {
        return myScreenY;
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


    // Will be used to later on to allow res.player to bypass door without trivia.
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

    // Will be used later to allow res.player to reset door. Field and method may be moved to DOOR class
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

    /**
     * Gets the player's time limit.
     * @return myTimeLimit
     */
    public int getTimeLimit() {
        return myTimeLimit;
    }

    /**
     * Sets the player's time limit.
     * @param time time limit to be set
     */
    public void setTimeLimit(final int time) {
        myTimeLimit = time;
    }

    /**
     * Adds to player's backpack
     * @param theStone the stone to be added
     */
    public void addToBackpack(Stone theStone) {
        if (theStone.getStoneName().equals("Soul Stone")) setSoulStone(true);
        if (theStone.getStoneName().equals("Space Stone")) setSpaceStone(true);
        myBackpack.addToBackpack(theStone);
    }

    /**
     * Uses the specified stone then deletes it from backpack;
     * @param theStone theStone to be used.
     */
    public void useStone(final Stone theStone) {
        int stoneIndex = myBackpack.findStone(theStone);
        if (stoneIndex < 0) {
            throw new IllegalArgumentException("Player does not have this stone.");
        } else {
            Stone stone = myBackpack.getStone(stoneIndex);
            stone.useAbility(this);
            if (stone.getUses() == 0) {
                myBackpack.deleteStone(stoneIndex);
            }
            if (stone.getStoneName().equals("Soul Stone")) setSoulStone(false);
            if (stone.getStoneName().equals("Space Stone")) setSpaceStone(false);
        }
    }

    /**
     * Display player's backpack.
     */
    public void displayBackpack() {
        myBackpack.displayCurrInventory();
    }

    /**
     * Returns number of Stones that is in res.player's backpack.
     * @return number of Stones that is in res.player's backpack
     */
    public int getCurrItem() {
        return myBackpack.getCurrItems();
    }

    /**
     * Takes the stone that is in the chest.
     * @param theChest the chest to take from
     */
    public void takeStone(final Chest theChest) {
        if (theChest.getMyStone() == null) {
            throw new NullPointerException("Chest is empty");
        } else {
            addToBackpack(theChest.getMyStone());
            theChest.clearChest();
        }
    }

    public int getMyWorldY() {
        return myWorldY;
    }

    public void setMyWorldY(int myWorldY) {
        this.myWorldY = myWorldY;
    }

    public int getMyWorldX() {
        return myWorldX;
    }

    public void setMyWorldX(int myWorldX) {
        this.myWorldX = myWorldX;
    }
}