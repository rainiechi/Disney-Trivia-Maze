package View;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel myGp;
    private Tile[] myTile;
    private int myMapTileNum[][];

    public TileManager(GamePanel gp) {
        this.myGp = gp;

        myTile = new Tile[20];
        myMapTileNum = new int[gp.getMaxWorldCol()][gp.getMaxWorldRow()];
        getTileImage();
        loadMap("/res/map/maze_ver2.txt");
    }

    /**
     * Method creates an array of res.tiles and sets image of res.tiles
     * @throws IOException if image is not found
     */
    public void getTileImage() {
        for (int i = 0; i < 16; i++) {
            myTile[i] = new Tile();
        }
        try {
            myTile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/black.png")));
            myTile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/wood_floor.png")));
            myTile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_wallV3.png")));
            myTile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/top_edge.png")));
            myTile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/left_corner.png")));
            myTile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/left_edge.png")));
            myTile[6].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_edge.png")));
            myTile[7].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/side_edge.png")));
            myTile[8].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/right_corner.png")));
            myTile[9].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_left_corner.png")));
            myTile[10].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall_door.png")));
            myTile[11].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_right_corner.png")));
            myTile[12].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/hallway_bottom_left.png")));
            myTile[13].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/hallway_bottom_right.png")));
            myTile[14].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/top_door_right.png")));
            myTile[15].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/top_door_left.png")));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Reads 2D array text file to load map of res.tiles.
     * @param theFile text file
     */
    public void loadMap(String theFile) {
        try {
            InputStream is = getClass().getResourceAsStream(theFile);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;

            while (col < myGp.getMaxWorldCol() && row < myGp.getMaxWorldRow()) {
                String line = br.readLine();

                while (col < myGp.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    myMapTileNum[col][row] = num;
                    col++;

                }
                if (col == myGp.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2) {
    int worldCol = 0;
    int worldRow = 0;
    int x = 0;
    int y = 0;

    while (worldCol < myGp.getMaxWorldCol() && worldRow < myGp.getMaxWorldRow()) {
        int tileNum = myMapTileNum[worldCol][worldRow];
        g2.drawImage(myTile[tileNum].getImage(), x, y, myGp.getTileSize(), myGp.getTileSize(), null);
        worldCol++;
        x += myGp.getTileSize();

        if (worldCol == myGp.getMaxWorldCol()) {
            worldCol = 0;
            x = 0;
            worldRow++;
            y += myGp.getTileSize();
        }
    }
    }
}
