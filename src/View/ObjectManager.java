package View;

import Model.Chest;
import Model.Door;
import Model.GameSettings;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class ObjectManager {
    public BufferedImage myImage;
    private String myName;
    private int myWorldX;
    private int myWorldY;
    private GameSettings myGs;
    private Rectangle mySolidArea;
    private int mySolidAreaDefaultX;
    private int mySolidAreaDefaultY;
    private boolean myCollision;
    private boolean myTouchedObj;
    private Door myDoor;
    private Chest myChest;
    private boolean myLocked;

    public ObjectManager(GameSettings theGs, String theName, int theWorldX, int theWorldY, boolean theCheck) {
        myName = theName;
        this.myGs = theGs;
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
    public void setLocked(boolean b) {
        myLocked = b;
    }
    public boolean isLocked() {
        return myLocked;
    }
    public void draw(Graphics2D theG2, GamePanel theGp) {
        int screenX = myWorldX - theGp.getPlayerManager().getMyWorldX() + theGp.getPlayerManager().getMyX();
        int screenY = myWorldY - theGp.getPlayerManager().getMyWorldY() + theGp.getPlayerManager().getMyY();

        if (myWorldX + myGs.getTileSize() > theGp.getPlayerManager().getMyWorldX() - theGp.getPlayerManager().getMyX() &&
                myWorldX - myGs.getTileSize() < theGp.getPlayerManager().getMyWorldX() + theGp.getPlayerManager().getMyX() &&
                myWorldY + myGs.getTileSize() > theGp.getPlayerManager().getMyWorldY()  - theGp.getPlayerManager().getMyY() &&
                myWorldY - myGs.getTileSize() < theGp.getPlayerManager().getMyWorldY()  + theGp.getPlayerManager().getMyY()) {

            theG2.drawImage(myImage, screenX, screenY, myGs.getTileSize(), myGs.getTileSize(), null);
        }
    }
    public void setDoor(Door theDoor) {
        myDoor = theDoor;
    }
    public Door getDoor() {
        return myDoor;
    }
    public void setChest(Chest theChest) {
        myChest = theChest;
    }
    public Chest getChest() {
        return myChest;
    }
    public void setWorldX (int theX) {
        myWorldX = theX;
    }
    public int getWorldX() {
        return myWorldX;
    }
    public int getWorldY() {
        return myWorldY;
    }
    public void setWorldY (int theY) {
        myWorldY = theY;
    }
    public String getName() {
        return myName;
    }
    public void setSolidArea(Rectangle theArea) {
        mySolidArea = theArea;
    }
    public Rectangle getSolidArea() {
        return mySolidArea;
    }
    public boolean isCollision() {
        return myCollision;
    }
    public void setCollision(boolean theCheck) {
        myCollision = theCheck;
    }
    public void setSolidAreaX(int theArea) {
        mySolidArea.x = theArea;
    }
    public int getSolidAreaX() {
        return mySolidArea.x;
    }
    public void setSolidAreaY(int theArea) {
        mySolidArea.y = theArea;
    }
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
    public void setMyImage(BufferedImage theImage) {
        myImage = theImage;
    }
    public boolean isTouched() {
        return myTouchedObj;
    }
    public void setTouched(boolean theBoolean) {
        myTouchedObj = theBoolean;
    }
}
