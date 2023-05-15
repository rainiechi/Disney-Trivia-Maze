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
    public int getMyMapTileNum(int col, int row) {
        return myMapTileNum[col][row];
    }
    public int[][] getMaze() {
        return myMapTileNum;
    }
    public Tile getTile(int num) {
        return myTile[num];
    }
}
