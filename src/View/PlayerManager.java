package View;

import Model.GameSettings;
import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;

public class PlayerManager implements Serializable {
    /**
     * Images for sprite.
     */
    private transient BufferedImage myUp1, myUp2, myDown1, myDown2, myLeft1, myLeft2, myRight1, myRight2, myHead;
    /**
     * Player's direction.
     */
    private String myDirection;

    /**
     * Sprite counter to help coordinate sprite images.
     */
    private int mySpriteCounter;
    /**
     * Sprite number to help coordinate sprite images.
     */
    private int mySpriteNum;
    /**
     * The hosting game panel.
     */
    private GamePanel myGp;
    /**
     * The player.
     */
    private Player myPlayer;
    /**
     * Player's x coordinate.
     */
    private int myX;
    /**
     * Player's y coordinate.
     */
    private int myY;
    /**
     * Player's world x coordinate.
     */
    private int myWorldX;
    /**
     * Player's world y coordinate.
     */
    private int myWorldY;
    /**
     * Solid area to determine collision
     */
    private Rectangle mySolidArea;
    /** Default x coordinate of solid area */
    private int mySolidAreaDefaultX;
    /** Default y coordinate of solid area */
    private int mySolidAreaDefaultY;
    /**
     * If the character collided.
     */
    private boolean myCollision;

    /**
     * The keyhandler for the player.
     */
    private KeyHandler myKeyH;

    /**
     * Constructor initializes the fields.
     * @param theGp GamePanel passed in.
     * @param theKeyH KeyHandler passed in
     * @param thePlayer Player passed in
     */
    public PlayerManager(final GamePanel theGp, final KeyHandler theKeyH, final Player thePlayer) {
        if (theGp == null || theKeyH == null || thePlayer == null) {
            throw new IllegalArgumentException("Please enter non-null parameters");
        }
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
        // starting direction shown in game
        myDirection = "down";
    }

    /**
     * Sets BufferedImage fields to images.
     */
    public void setPlayerImage() {
        try {
            myUp1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_up1.png"));
            myUp2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_up2.png"));
            myDown1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_down1.png"));
            myDown2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_down2.png"));
            myLeft1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_left.png"));
            myLeft2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_left2.png"));
            myRight1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_right1.png"));
            myRight2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_right2.png"));
            myHead = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/mike_stand.png"));
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
            myGp.getCC().pickUpObject(objIndex, myKeyH, this);

            if (!myCollision) {
                switch(myDirection) {
                    case "up": myWorldY -= myPlayer.getPlayerSpeed(); break;
                    case "down": myWorldY += myPlayer.getPlayerSpeed(); break;
                    case "left": myWorldX -= myPlayer.getPlayerSpeed(); break;
                    case "right": myWorldX += myPlayer.getPlayerSpeed(); break;
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
        return myPlayer.getPlayerSpeed();
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
    public void setSolidAreaX(final int theArea) {
        mySolidArea.x = theArea;
    }
    /**
     * Setter method for mySolidArea.y.
     * @param theArea area to be set.
     */
    public void setSolidAreaY(final int theArea) {
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

    /**
     * Getter for player object.
     * @return myPlayer.
     */
    public Player getPlayer() {
        return myPlayer;
    }

}