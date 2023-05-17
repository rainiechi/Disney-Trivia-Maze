package View;

import Model.GameSettings;
import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerManager {
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String myDirection;

    private int spriteCounter;
    private int spriteNum;
    private GameSettings myGs;
    private GamePanel myGp;

    Player myPlayer;
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
        spriteNum = 1;
        spriteCounter = 0;
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
        mySpeed = myPlayer.getPlayerSpeed();

        myDirection = "down";
    }
    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_up1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_up2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_down_left.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_down_right.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_left_1.xcf.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_left_2.xcf.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_right_1.xcf.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/playerAssets/girl_sprite_right_2.xcf.png"));
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
            spriteCounter++;
            if (spriteCounter > 12) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
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
                    PopUp pop = new PopUp();
                    break;
            }
        }
    }
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(myDirection) {
            case "up":
                if (spriteNum == 1) {
                    image = up1;
                }
                if (spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if (spriteNum == 1) {
                    image = down1;
                }
                if (spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if (spriteNum == 1) {
                    image = left1;
                }
                if (spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if (spriteNum == 1) {
                    image = right1;
                }
                if (spriteNum == 2) {
                    image = right2;
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
}
