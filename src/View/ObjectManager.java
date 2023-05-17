package View;

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
    GameSettings myGs;
    private Rectangle mySolidArea;
    private int mySolidAreaDefaultX;
    private int mySolidAreaDefaultY;
    private boolean myCollision;

    public ObjectManager(GameSettings theGs, String theName) {
        myName = theName;
        this.myGs = theGs;
        switchObject();
        mySolidArea= new Rectangle(0,0,48,48);
        mySolidAreaDefaultX = mySolidArea.x;
        mySolidAreaDefaultY = mySolidArea.y;
        myCollision = false;

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
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
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
    public void setSolidAreaY(int theArea) {
        mySolidArea.y = theArea;
    }
    public int getMySolidAreaDefaultY() {
        return mySolidAreaDefaultY;
    }

    public int getMySolidAreaDefaultX() {
        return mySolidAreaDefaultX;
    }
}
