package Model;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage myTileImage;

    private boolean myCollision;

    public Tile() {
        myCollision = false;
    }

    public BufferedImage getImage() {
        return myTileImage;
    }
    public void setImage(BufferedImage read) {
        myTileImage = read;
    }
    public void setCollision(boolean collision) {
        myCollision = collision;
    }
    public boolean isCollision() {
        return myCollision;
    }
}