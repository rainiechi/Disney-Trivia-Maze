package View;

import Model.Chest;
import Model.Door;
import Model.GameSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectManager {

    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    /**
     * Private BufferedImage field for image
     */
    private BufferedImage myImage;
    /** Private String field for name of object */
    private String myName;
    /** Private int field for X coordinate on map */
    private int myWorldX;
    /** Private int field for Y coordinate on map  */
    private int myWorldY;

    /** Private Rectangle field for solid area of object */
    private Rectangle mySolidArea;
    /** Private int field for solid area default value x of object */
    private int mySolidAreaDefaultX;
    /** Private int field for solid area default value y of object */
    private int mySolidAreaDefaultY;
    /** Private boolean field for collision */
    private boolean myCollision;
    /** Private boolean field for if object has been touched */
    private boolean myTouchedObj;
    /** Private boolean field for if object has been locked */
    private boolean myLocked;
    /** Private Door object */
    private Door myDoor;
    /** Private Chest object */
    private Chest myChest;


    /**
     * Constructor initializes fields.
     * @param theName name of object
     * @param theWorldX X coordinate on map
     * @param theWorldY Y coordinate on map
     * @param theCheck collision check
     */
    public ObjectManager(final String theName, final int theWorldX, final int theWorldY, final boolean theCheck) {
        myName = theName;
        switchObject();
        mySolidArea= new Rectangle(0,0,48,48);
        mySolidAreaDefaultX = mySolidArea.x;
        mySolidAreaDefaultY = mySolidArea.y;
        myCollision = theCheck;
        myWorldX = theWorldX;
        myWorldY = theWorldY;
        myLocked = false;
        myTouchedObj = false;
        myDoor = null;
        myChest = null;
    }

    /**
     * Method identifies object by name and sets image based on name of object.
     */
    public void switchObject() {
        if (myName != null) {
            try{
                switch (myName) {
                    case "Door":
                        myImage = (ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall_door.png")));
                        break;
                    case "Exit":
                        myImage = (ImageIO.read(getClass().getResourceAsStream("/res/tiles/exit_door.png")));
                        break;
                    case "Chest":
                        myImage = (ImageIO.read(getClass().getResourceAsStream("/res/tiles/chest.png")));
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method draws the object.
     * @param theG2 Graphics2D object to draw
     * @param theGp GamePanel passed to constructor
     */
    public void draw(final Graphics2D theG2, final GamePanel theGp) {
        int screenX = myWorldX - theGp.getPlayerManager().getMyWorldX() + theGp.getPlayerManager().getMyX();
        int screenY = myWorldY - theGp.getPlayerManager().getMyWorldY() + theGp.getPlayerManager().getMyY();

        if (myWorldX + TILE_SIZE > theGp.getPlayerManager().getMyWorldX() - theGp.getPlayerManager().getMyX() &&
                myWorldX - TILE_SIZE < theGp.getPlayerManager().getMyWorldX() + theGp.getPlayerManager().getMyX() &&
                myWorldY + TILE_SIZE > theGp.getPlayerManager().getMyWorldY()  - theGp.getPlayerManager().getMyY() &&
                myWorldY - TILE_SIZE < theGp.getPlayerManager().getMyWorldY()  + theGp.getPlayerManager().getMyY()) {

            theG2.drawImage(myImage, screenX, screenY, TILE_SIZE, TILE_SIZE, null);
        }
    }

    /**
     * Setter method for Door object.
     * @param theDoor door object
     */
    public void setDoor(Door theDoor) {
        myDoor = theDoor;
    }

    /**
     * Getter method for myDoor.
     * @return myDoor door object
     */
    public Door getDoor() {
        return myDoor;
    }

    /**
     * Setter method for Chest object
     * @param theChest chest object
     */
    public void setChest(Chest theChest) {
        myChest = theChest;
    }

    /**
     * Getter method for myChest
     * @return myChest chest object
     */
    public Chest getChest() {
        return myChest;
    }

    /**
     * Getter method for myWorldX.
     * @return myWorldX
     */
    public int getWorldX() {
        return myWorldX;
    }

    /**
     * Getter method for myWorldY
     * @return myWorldY
     */
    public int getWorldY() {
        return myWorldY;
    }

    /**
     * Getter method for myName
     * @return myName
     */
    public String getName() {
        return myName;
    }

    /**
     * Setter method for solid area.
     * @param theArea rectangle object to be set.
     */
    public void setSolidArea(Rectangle theArea) {
        mySolidArea = theArea;
    }

    /**
     * Getter method for solid area.
     * @return mySolidArea rectangle object
     */
    public Rectangle getSolidArea() {
        return mySolidArea;
    }

    /**
     * Getter method for myCollision.
     * @return true if collision, false otherwise.
     */
    public boolean isCollision() {
        return myCollision;
    }

    /**
     * Setter method for mySolidArea.x
     * @param theArea int area to be set.
     */
    public void setSolidAreaX(int theArea) {
        mySolidArea.x = theArea;
    }

    /**
     * Getter method for mySolidArea.x
     * @return mySolidArea.x
     */
    public int getSolidAreaX() {
        return mySolidArea.x;
    }

    /**
     * Setter method for mySolidArea.y
     * @param theArea int area to be set.
     */
    public void setSolidAreaY(int theArea) {
        mySolidArea.y = theArea;
    }

    /**
     * Getter method for mySolidArea.y
     * @return mySolidArea.y
     */
    public int getSolidAreaY() {
        return mySolidArea.y;
    }
    public int getMySolidAreaDefaultY() {
        return mySolidAreaDefaultY;
    }
    public void setMySolidAreaDefaultY(int theArea) { mySolidAreaDefaultY = theArea; }
    public int getMySolidAreaDefaultX() {
        return mySolidAreaDefaultX;
    }
    public void setSolidAreaDefaultX(int theArea) {
        mySolidAreaDefaultX = theArea;
    }

    /**
     * Setter method for myImage
     * @param theImage image passed in
     */
    public void setMyImage(BufferedImage theImage) {
        myImage = theImage;
    }
    public boolean isTouched() {
        return myTouchedObj;
    }
    public void setTouched(boolean theBoolean) {
        myTouchedObj = theBoolean;
    }
    /**
     * Setter method for myLocked.
     * @param b boolean passed in to set myLocked
     */
    public void setLocked(boolean b) {
        myLocked = b;
    }
    /**
     * Getter method for myLocked.
     * @return true if the object is locked, otherwise false.
     */
    public boolean isLocked() {
        return myLocked;
    }
}
