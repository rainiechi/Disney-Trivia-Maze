package View;

import Model.GameSettings;
import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class PlayerManager {
    private BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;
    private String direction;

    private int spriteCounter;
    private int spriteNum;
    GameSettings myGs;
    GamePanel myGp;
    Player myPlayer;
    private int myX;
    private int myY;
    private int myWorldX;
    private int myWorldY;
    private int mySpeed;

    KeyHandler myKeyH;

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
        myWorldX = myPlayer.getMyWorldX();
        myWorldY = myPlayer.getMyWorldY();
        myX = myPlayer.getScreenX();
        myY = myPlayer.getScreenY();
        mySpeed = myPlayer.getPlayerSpeed();

        direction = "down";
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
                direction = "up";
                myWorldY -= mySpeed;
            } else if (myKeyH.isDownPressed()) {
                direction = "down";
                myWorldY += mySpeed;
            } else if (myKeyH.isLeftPressed()) {
                direction = "left";
                myWorldX -= mySpeed;
            } else if (myKeyH.isRightPressed()) {
                direction = "right";
                myWorldX += mySpeed;
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
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch(direction) {
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

    public void setMyWorldX(int myWorldX) {
        this.myWorldX = myWorldX;
    }

    public int getMyWorldY() {
        return myWorldY;
    }

    public void setMyWorldY(int myWorldY) {
        this.myWorldY = myWorldY;
    }
    public int getMyX() {
        return myX;
    }
    public int getMyY() {
        return myY;
    }
}
