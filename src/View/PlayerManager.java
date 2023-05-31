package View;

import Model.Chest;
import Model.GameSettings;
import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class PlayerManager implements Serializable {
    private transient BufferedImage myUp1, myUp2, myDown1, myDown2, myLeft1, myLeft2, myRight1, myRight2, myHead;
    private String myDirection;

    private int mySpriteCounter;
    private int mySpriteNum;
    private GamePanel myGp;
    private Player myPlayer;
    private int myX;
    private int myY;
    private int myWorldX;
    private int myWorldY;
    private int mySpeed;
    private Rectangle mySolidArea;
    private int mySolidAreaDefaultX;
    private int mySolidAreaDefaultY;
    private boolean myCollision;

    private KeyHandler myKeyH;

    /**
     * Constructor initializes the fields.
     * @param theGp GamePanel passed in.
     * @param theKeyH KeyHandler passed in
     * @param thePlayer Player passed in
     */
    public PlayerManager(final GamePanel theGp, final KeyHandler theKeyH, final Player thePlayer) {
        myPlayer = thePlayer;
        myGp = theGp;
        myKeyH = theKeyH;
        mySpriteNum = 1;
        mySpriteCounter = 0;
        setDefaultValues();
        setPlayerImage();
    }


    /**
     * Sets default values for collision area, speed, and coordinates on maze.
     */
    public void setDefaultValues() {
        myCollision = false;
        mySolidArea = new Rectangle(6,16,44,44);
        mySolidAreaDefaultX = mySolidArea.x;
        mySolidAreaDefaultY = mySolidArea.y;
        myWorldX = myPlayer.getMyWorldX();
        myWorldY = myPlayer.getMyWorldY();
        myX = myPlayer.getScreenX();
        myY = myPlayer.getScreenY();
        mySpeed = 2 + myPlayer.getPlayerSpeed();

        myDirection = "down";
    }

    /**
     * Sets BufferedImage fields to images.
     */
    public void setPlayerImage() {
        try {
            myUp1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_up1.png"));
            myUp2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_up2.png"));
            myDown1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_down_left.png"));
            myDown2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_down_right.png"));
            myLeft1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_left_1.xcf.png"));
            myLeft2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_left_2.xcf.png"));
            myRight1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_right_1.xcf.png"));
            myRight2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_right_2.xcf.png"));
            myHead = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/sprite_headshot.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method updates the image of sprite character when a key is pressed.
     */
    public void update() {
        if (myKeyH.isDownPressed() || myKeyH.isLeftPressed() ||
                myKeyH.isRightPressed() || myKeyH.isUpPressed()) {

            if (myKeyH.isUpPressed()) {
                myDirection = "up";
            } else if (myKeyH.isDownPressed()) {
                myDirection = "down";
            } else if (myKeyH.isLeftPressed()) {
                myDirection = "left";
            } else if (myKeyH.isRightPressed()) {
                myDirection = "right";
            }

            myCollision = false;
            myGp.getCC().checkTile(this);

            int objIndex = myGp.getCC().checkObject(this, true);
            myGp.getCC().pickUpObject(objIndex, myKeyH, this, new Chest(myGp.getGame().getMyStoneManager()));

            if (!myCollision) {
                switch(myDirection) {
                    case "up": myWorldY -= mySpeed; break;
                    case "down": myWorldY += mySpeed; break;
                    case "left": myWorldX -= mySpeed; break;
                    case "right": myWorldX += mySpeed; break;
                }
            }
            mySpriteCounter++;
            if (mySpriteCounter > 12) {
                if (mySpriteNum == 1) {
                    mySpriteNum = 2;
                } else if (mySpriteNum == 2) {
                    mySpriteNum = 1;
                }
                mySpriteCounter = 0;
            }
        }
    }

    /**
     * Method draws the sprite.
     * @param theG2 Graphics2D object
     */
    public void draw(final Graphics2D theG2) {
        BufferedImage image = null;

        switch(myDirection) {
            case "up":
                if (mySpriteNum == 1) {
                    image = myUp1;
                }
                if (mySpriteNum == 2) {
                    image = myUp2;
                }
                break;
            case "down":
                if (mySpriteNum == 1) {
                    image = myDown1;
                }
                if (mySpriteNum == 2) {
                    image = myDown2;
                }
                break;
            case "left":
                if (mySpriteNum == 1) {
                    image = myLeft1;
                }
                if (mySpriteNum == 2) {
                    image = myLeft2;
                }
                break;
            case "right":
                if (mySpriteNum == 1) {
                    image = myRight1;
                }
                if (mySpriteNum == 2) {
                    image = myRight2;
                }
                break;
        }
        theG2.drawImage(image, myX, myY, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE, null);

    }

    /**
     * Getter method for myWorldX.
     * @return myWorldX
     */
    public int getMyWorldX() {
        return myWorldX;
    }

    /**
     * Getter method for myWorldY.
     * @return myWorldY
     */
    public int getMyWorldY() {
        return myWorldY;
    }
    /**
     * Getter method for myX.
     * @return myX.
     */
    public int getMyX() {
        return myX;
    }
    /**
     * Getter method for myY.
     * @return myY.
     */
    public int getMyY() {
        return myY;
    }

    /**
     * Getter method for player speed.
     * @return player speed.
     */
    public int getSpeed() {
        return mySpeed;
    }

    /**
     * Getter method for player direction.
     * @return player direction.
     */
    public String getDirection() {
        return myDirection;
    }

    /**
     * Setter method for collision.
     * @param theCollision boolean
     */
    public void setCollision(final boolean theCollision) {
        myCollision = theCollision;
    }
    /**
     * Getter method for collision.
     * @return myCollision
     */
    public boolean isCollision() {
        return myCollision;
    }

    /**
     * Getter method for solid area.
     * @return mySolidArea
     */
    public Rectangle getSolidArea() {
        return mySolidArea;
    }

    /**
     * Setter method for mySolidArea.x.
     * @param theArea area to be set.
     */
    public void setSolidAreaX(int theArea) {
        mySolidArea.x = theArea;
    }
    /**
     * Setter method for mySolidArea.y.
     * @param theArea area to be set.
     */
    public void setSolidAreaY(int theArea) {
        mySolidArea.y = theArea;
    }

    /**
     * Getter method for the default value of
     * mySolidArea.y.
     * @return mySolidAreaDefaultY default value
     */
    public int getMySolidAreaDefaultY() {
        return mySolidAreaDefaultY;
    }
    /**
     * Getter method for the default value of
     * mySolidArea.x.
     * @return mySolidAreaDefaultX default value
     */
    public int getMySolidAreaDefaultX() {
        return mySolidAreaDefaultX;
    }

    /**
     * Getter method for BufferedImage of sprite headshot.
     * @return myHead, image of sprite head.
     */
    public BufferedImage getMyHead() {
        return myHead;
    }


    public void setMyKeyH(final KeyHandler theKeyH) {
        myKeyH = theKeyH;
    }
    public Player getPlayer() {
        return myPlayer;
    }

}
