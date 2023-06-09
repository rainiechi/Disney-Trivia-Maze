package Model;

import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Tile class represents tiles in world map.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class Tile implements Serializable {
    /** Image of tile */
    private transient BufferedImage myTileImage;
    /** Boolean collision */
    private boolean myCollision;

    /**
     * Constructor initializes necessary fields.
     */
    public Tile() {
        myCollision = false;
    }

    /**
     * Getter method for tile image.
     * @return BufferedImage of tile
     */
    public BufferedImage getImage() {
        return myTileImage;
    }
    /**
     * Setter method for tile image.
     * @param read BufferedImage of tile
     */
    public void setImage(final BufferedImage read) {
        myTileImage = read;
    }

    /**
     * Setter method for collision.
     * @param collision boolean
     */
    public void setCollision(final boolean collision) {
        myCollision = collision;
    }

    /**
     * Getter method for collision.
     * @return boolean collision
     */
    public boolean isCollision() {
        return myCollision;
    }
}