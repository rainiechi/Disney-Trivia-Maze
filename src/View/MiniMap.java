package View;

import Model.GameSettings;
import Model.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class MiniMap implements Serializable {
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    private static final int MAX_WORLD_COL = GameSettings.MAX_WORLD_COLUMN;
    private static final int MAX_WORLD_ROW = GameSettings.MAX_WORLD_ROW;
    private static final int SCREEN_WIDTH = GameSettings.SCREEN_WIDTH;


    /** private GamePanel object field */
    private GamePanel myGp;

    /** Private field for image */
    private transient BufferedImage myMazeMap;
    /** Private boolean field if map is enabled */
    private boolean myMapEnabled;
    /** private Maze object field */
    private transient Maze myMaze;



    /**
     * Constructor initializes the fields.
     * @param theGp GamePanel passed to constructor
     */
    public MiniMap(GamePanel theGp) {
        myGp = theGp;
        myMaze = new Maze();
        createWorld();
    }

    /**
     * Method draws the map.
     */
    public void createWorld() {
        int worldMapWidth = TILE_SIZE * MAX_WORLD_COL;
        int worldMapHeight = TILE_SIZE * MAX_WORLD_ROW;

        myMazeMap = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = myMazeMap.createGraphics();

        int col = 0;
        int row = 0;

        while (col < MAX_WORLD_COL && row < MAX_WORLD_ROW) {
            int tileNum = myMaze.getMyMapTileNum(col, row);
            int x = TILE_SIZE * col;
            int y = TILE_SIZE * row;
            g2.drawImage(myMaze.getTile(tileNum).getImage(), x, y, null);
            col++;
            if (col == MAX_WORLD_COL) {
                col = 0;
                row++;
            }
        }

    }

    /**
     * Method draws the maze and player scaled down so it is a mini map.
     * @param theG2 graphics2D object
     */
    public void drawMiniMapScreen(Graphics2D theG2) {
        if (myMapEnabled) {

            // Draw Map
            int width = 200;
            int height = 200;
            int x = SCREEN_WIDTH - width - 50;
            int y = 50;
            theG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            theG2.drawImage(myMazeMap, x, y, width, height, null);

            // Draw player
            double scale = (double) (TILE_SIZE * MAX_WORLD_COL) / width;
            int playerX = (int) (x + myGp.getPlayerManager().getMyWorldX() / scale) - 15;
            int playerY = (int) (y + myGp.getPlayerManager().getMyWorldY() / scale);
            int playerSize = (TILE_SIZE /2);

            theG2.drawImage(myGp.getPlayerManager().getMyHead(), playerX, playerY, playerSize, playerSize, null);
            theG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    /**
     * Setter method for myMapEnabled.
     * @param theEnabled boolean to be set.
     */
    public void setMapEnabled(boolean theEnabled) {
        myMapEnabled = theEnabled;
    }

    /**
     * Getter method for myMapEnabled.
     * @return true if enabled, false otherwise.
     */
    public boolean isMapEnabled() {
        return myMapEnabled;
    }
}
