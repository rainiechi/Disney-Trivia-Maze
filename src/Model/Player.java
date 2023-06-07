package Model;


import java.io.Serializable;

/**
 * Player class contains the state of a player and methods.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class Player implements Serializable {
    /** Backpack object */
    private Backpack myBackpack;
    /** X position on screen */
    private int myScreenX;
    /** Y position on screen */
    private int myScreenY;
    /** X position on world map */
    private int myWorldX;
    /** Y position on world map */
    private int myWorldY;
    /** Player speed */
    private int myPlayerSpeed;
    /** Time limit */
    private int myTimeLimit;
    /** Boolean for space stone */
    private boolean mySpaceStone;
    /** Boolean for Soul stone */
    private boolean mySoulStone;
    /** Game settings */
    private transient GameSettings myGs;
    /** Health of the player */
    private int myHealth;

    /**
     * Player constructor initializes fields.
     */
    public Player() {
        myGs = new GameSettings();
        myBackpack = new Backpack();
        myHealth = 3;

        myScreenX = GameSettings.SCREEN_WIDTH/2 - (GameSettings.TILE_SIZE/2);
        myScreenY = GameSettings.SCREEN_HEIGHT/2 - (GameSettings.TILE_SIZE/2);
        myWorldX = GameSettings.TILE_SIZE * 38;
        myWorldY = GameSettings.TILE_SIZE * 68;
        myPlayerSpeed = 4; //change back to 3
        mySpaceStone = false;
        mySoulStone = false;
        myTimeLimit = 15;
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
    public void decreaseHealth() {
        if (myHealth > 0) {
            myHealth--;
        }
    }

    /**
     * Uses the specified stone then deletes it from backpack;
     * @param theStone theStone to be used.
     */
    public void useStone(final Stone theStone) {
        int stoneIndex = myBackpack.findStone(theStone);

        //System.out.println("The stone is a Mind stone "+theStone.getStoneName());
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

    /**
     * Display player's backpack.
     */
    public void displayBackpack() {
        myBackpack.displayCurrInventory();
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
     * @param theSpeed speed to set.
     */
    public void setPlayerSpeed(final int theSpeed) {
        myPlayerSpeed = theSpeed;
    }


    /**
     * Checks if player has a space stone.
     * @return true if player has a space stone, otherwise false.
     */
    public boolean isSpaceStone() { return mySpaceStone;}

    /**
     * Sets space stone to true or false.
     * @param check boolean to be passed in.
     */
    public void setSpaceStone(boolean check) {
        mySpaceStone = check;
    }


    /**
     * Checks if player has a soul stone.
     * @return true if player has a soul stone, otherwise false.
     */
    public boolean isSoulStone() { return mySoulStone;}

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
     * Returns number of Stones that is in player's backpack.
     * @return number of Stones that is in player's backpack
     */
    public int getCurrItem() {
        return myBackpack.getCurrItems();
    }


    /**
     * Getter method for Y position on world map.
     * @return Y position on world map
     */
    public int getMyWorldY() {
        return myWorldY;
    }
    /**
     * Getter method for X position on world map.
     * @return X position on world map
     */
    public int getMyWorldX() {
        return myWorldX;
    }

    /**
     * Getter method for backpack.
     * @return player's backpack.
     */
    public Backpack getBackpack() {
        return myBackpack;
    }

    /**
     * Getter method for health.
     * @return player's health.
     */
    public int getHealth() {
        return myHealth;
    }
}