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
/**
 The PlayerHealth class represents the health bar of a player in a game.
 It is responsible for rendering the player's health as a visual representation on the screen.
 */
public class PlayerHealth {
    /**
     * The heart image.
     */
    private transient BufferedImage myImage;
    /**
     * The font used for health.
     */
    private Font myArial30;
    /**
     * The player object associated with the health bar.
     */
    private Player myPlayer;

    /**
     Constructs a PlayerHealth object for the specified player.
     @param thePlayer The player object for which the health bar is created.
     */

    public PlayerHealth (final Player thePlayer) {
        if (thePlayer == null) {
            throw new IllegalArgumentException("Please enter non-null player");
        }
        myArial30 = new Font("Arial", Font.BOLD, 30);
        myPlayer = thePlayer;
        try {
            myImage = (ImageIO.read(getClass().getResourceAsStream("/res/Miscellaneous_images/heart pixel art 32x32.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     Draws the player's health bar on the specified Graphics2D object.
     @param g2 The Graphics2D object on which the health bar is drawn.
     */
    public void draw(final Graphics2D g2) {
        if (myPlayer.getHealth() > 0) {
            g2.setFont(myArial30);
            g2.setColor(Color.white);

            g2.drawImage(myImage, GameSettings.TILE_SIZE / 2, GameSettings.TILE_SIZE / 2, GameSettings.TILE_SIZE, GameSettings.TILE_SIZE, null);
            g2.drawString("X " + myPlayer.getHealth(), 100, 65);
        }
    }

    /**
     Custom serialization method for the object. Writes the object's state to the output stream.
     @param out The output stream to write the object to.
     @throws IOException If an I/O error occurs while writing the object.
     */
    private void writeObject(final ObjectOutputStream out) throws IOException {
        out.defaultWriteObject();

        // Serialize the image data
        if (myImage != null) {
            ImageIO.write(myImage, "png", out);
        }
    }
    /**
     Custom deserialization method for the object. Reads the object's state from the input stream.
     @param in The input stream to read the object from.
     @throws IOException If an I/O error occurs while reading the object.
     @throws ClassNotFoundException If the class of the object being deserialized cannot be found.
     */
    private void readObject(final ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();

        // Deserialize the image data
        try {
            myImage = ImageIO.read(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
