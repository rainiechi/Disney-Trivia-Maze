package View;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TileManager {
    GamePanel myGp;
    private Tile[] myTile;
    private int myMapTileNum[][];

    public TileManager(GamePanel gp) {
        this.myGp = gp;

        myTile = new Tile[15];
        myMapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
       // getTileImage();
    }
    public void getTileImage() {

        try {

            myTile[0] = new Tile();
            myTile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/")));

            myTile[1] = new Tile();
            myTile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/")));

            myTile[2] = new Tile();
            myTile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/")));

            myTile[3] = new Tile();
            myTile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/")));

            myTile[4] = new Tile();
            myTile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/")));

            myTile[5] = new Tile();
            myTile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/")));


            myTile[6] = new Tile();
            myTile[6].setImage(ImageIO.read(getClass().getResourceAsStream("/tiles/")));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
