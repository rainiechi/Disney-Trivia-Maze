package Model;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Maze {
    GameSettings myGs;
    private Tile[] myTile;

    private int [][]  myMapTileNum;

    /**
     * Constructor intializes fields.
     * @param gs GameSettings
     */
    public Maze(GameSettings gs) {
        myTile = new Tile[20];
        this.myGs = gs;
        myMapTileNum = new int[myGs.getMaxWorldCol()][myGs.getMaxWorldRow()];
        loadMap("/res/map/maze_ver2.txt");
        getTileImage();
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

            while (col < myGs.getMaxWorldCol() && row < myGs.getMaxWorldRow()) {
                String line = br.readLine();

                while (col < myGs.getMaxWorldCol()) {
                    String numbers[] = line.split(" ");

                    int num = Integer.parseInt(numbers[col]);
                    myMapTileNum[col][row] = num;
                    col++;

                }
                if (col == myGs.getMaxWorldCol()) {
                    col = 0;
                    row++;
                }
            }
            br.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates Tile arrays and sets each index to the image.
     */
    public void getTileImage() {
        for (int i = 0; i < 16; i++) {
            myTile[i] = new Tile();
        }
        try {
            myTile[0].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/black.png")));
            myTile[0].setCollision(true);
            myTile[1].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/wood_floor.png")));
            myTile[2].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_wallV3.png")));
            myTile[2].setCollision(true);
            myTile[3].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/top_edge.png")));
            myTile[3].setCollision(true);
            myTile[4].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/left_corner.png")));
            myTile[4].setCollision(true);
            myTile[5].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/left_edge.png")));
            myTile[5].setCollision(true);
            myTile[6].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_edge.png")));
            myTile[6].setCollision(true);
            myTile[7].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/side_edge.png")));
            myTile[7].setCollision(true);
            myTile[8].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/right_corner.png")));
            myTile[8].setCollision(true);
            myTile[9].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_left_corner.png")));
            myTile[9].setCollision(true);
            myTile[10].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/wall_door.png")));
            myTile[11].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/bottom_right_corner.png")));
            myTile[11].setCollision(true);
            myTile[12].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/hallway_bottom_left.png")));
            myTile[12].setCollision(true);
            myTile[13].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/hallway_bottom_right.png")));
            myTile[13].setCollision(true);
            myTile[14].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/top_door_right.png")));
            myTile[14].setCollision(true);
            myTile[15].setImage(ImageIO.read(getClass().getResourceAsStream("/res/tiles/top_door_left.png")));
            myTile[15].setCollision(true);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /**
     * Getter method for number based on column and row.
     * @param col number of columns
     * @param row number of rows
     * @return integer value in column and row passed in.
     */
    public int getMyMapTileNum(int col, int row) {
        return myMapTileNum[col][row];
    }

    /**
     * Getter method for 2D array of tiles.
     * @return 2D of map tiles
     */
    public int[][] getMaze() {
        return myMapTileNum;
    }

    /**
     * Getter method for Tile
     * @param num index of Tile
     * @return Tile
     */
    public Tile getTile(int num) {
        return myTile[num];
    }
}
