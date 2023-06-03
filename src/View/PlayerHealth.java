package View;

import Model.GameSettings;
import Model.Player;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PlayerHealth implements Serializable  {
    private transient BufferedImage myImage;
    private Font myArial30;
    private Player myPlayer;
    public PlayerHealth (Player thePlayer) {
        myArial30 = new Font("Arial", Font.BOLD, 30);
        myPlayer = thePlayer;
        try {
            myImage = (ImageIO.read(getClass().getResourceAsStream("/res/Miscellaneous_images/heart pixel art 32x32.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {
        if (myPlayer.getHealth() > 0) {
            g2.setFont(myArial30);
            g2.setColor(Color.white);

            g2.drawImage(myImage, GameSettings.TILE_SIZE / 2, GameSettings.TILE_SIZE / 2, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE, null);
            g2.drawString("X " + myPlayer.getHealth(), 100, 65);
        } else {
            // losing scenario
        }
    }
    private void writeObject(ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        // Serialize the image data
        if (myImage != null) {
            ImageIO.write(myImage, "png", out);
        }
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // Deserialize the image data
        try {
            myImage = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
