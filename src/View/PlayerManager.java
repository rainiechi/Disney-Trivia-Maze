package View;

import Model.GameSettings;
import Model.MockQuestion;
import Model.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerManager {
    private BufferedImage myUp1, myUp2, myDown1, myDown2, myLeft1, myLeft2, myRight1, myRight2, myHead;
    private String myDirection;

    private int mySpriteCounter;
    private int mySpriteNum;
    private GameSettings myGs;
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

    public PlayerManager(GamePanel gp, KeyHandler keyH, GameSettings myGs, Player myPlayer) {
        this.myPlayer = myPlayer;
        this.myGp = gp;
        this.myGs = myGs;
        this.myKeyH = keyH;
        mySpriteNum = 1;
        mySpriteCounter = 0;
        setDefaultValues();
        getPlayerImage();
    }
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
    public void getPlayerImage() {
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
            pickUpObject(objIndex);

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
    public void pickUpObject(int i) {
        // Any number is fine as long as its not the index
        // of an object.
        if (i != 999) {
            String objectName = myGp.getObj()[i].getName();
            switch(objectName) {
                case "Door":
                    myGp.createPopUp();
                    break;

            }
        }
    }


    public void draw(Graphics2D g2) {
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
        g2.drawImage(image, myX, myY, myGs.getTileSize(), myGs.getTileSize(), null);

    }

    public int getMyWorldX() {
        return myWorldX;
    }


    public int getMyWorldY() {
        return myWorldY;
    }

    public int getMyX() {
        return myX;
    }
    public int getMyY() {
        return myY;
    }

    public int getSpeed() {
        return mySpeed;
    }

    public String getDirection() {
        return myDirection;
    }
    public void setCollision(boolean collision) {
        myCollision = collision;
    }
    public boolean isCollision() {
        return myCollision;
    }
    public Rectangle getSolidArea() {
        return mySolidArea;
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
    public BufferedImage getMyDown1() {
        return myDown1;
    }

    public BufferedImage getMyHead() {
        return myHead;
    }

    public void setMyHead(BufferedImage myHead) {
        this.myHead = myHead;
    }
}
