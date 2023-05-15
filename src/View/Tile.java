package View;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage myTileImage;

    private boolean myCollision = false;

    public BufferedImage getImage() {
        return myTileImage;
    }
    public void setImage(BufferedImage read) {
        myTileImage = read;
    }
}
