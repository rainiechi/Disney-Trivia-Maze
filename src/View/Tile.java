package View;

import java.awt.image.BufferedImage;

public class Tile {
    private BufferedImage myTileImage;

    private boolean myCollision = false;

    public void setImage(BufferedImage read) {
        myTileImage = read;
    }
}
