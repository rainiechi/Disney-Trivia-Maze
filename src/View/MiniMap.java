package View;

import Model.GameSettings;
import Model.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

/**
 * Class represents the mini map in game and contains the methods
 * to draw the mini map.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class MiniMap implements Serializable {
    /** Tile size */
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    /** Maximum columns in world map */
    private static final int MAX_WORLD_COL = GameSettings.MAX_WORLD_COLUMN;
    /** Maximum rows in world map */
    private static final int MAX_WORLD_ROW = GameSettings.MAX_WORLD_ROW;
    /** Width of screen */
    private static final int SCREEN_WIDTH = GameSettings.SCREEN_WIDTH;
    /** Width of world map */
    private static final int WORLD_MAP_WIDTH = TILE_SIZE * MAX_WORLD_COL;
    /** Height of world map */
    private static final int WORLD_MAP_HEIGHT = TILE_SIZE * MAX_WORLD_ROW;
    /** Maze object */
    private final static Maze MAZE = new Maze();
    /** Image of maze map */
    private static BufferedImage MAZE_MAP = new BufferedImage(WORLD_MAP_WIDTH, WORLD_MAP_HEIGHT, BufferedImage.TYPE_INT_ARGB);

    /** private GamePanel object field */
    private GamePanel myGp;

    /** Private boolean field if map is enabled */
    private boolean myMapEnabled;

    /**
     * Constructor initializes the fields.
     * @param theGp GamePanel passed to constructor
     */
    public MiniMap(GamePanel theGp) {
        if (theGp == null) {
            throw new IllegalArgumentException("Please enter non-null GamePanel");
        }
        myGp = theGp;
        createWorld();
    }

    /**
     * Method draws the map.
     */
    public void createWorld() {
        Graphics2D g2 = MAZE_MAP.createGraphics();

        int col = 0;
        int row = 0;

        while (col < MAX_WORLD_COL && row < MAX_WORLD_ROW) {
            int tileNum = MAZE.getMyMapTileNum(col, row);
            int x = TILE_SIZE * col;
            int y = TILE_SIZE * row;
            g2.drawImage(MAZE.getTile(tileNum).getImage(), x, y, null);
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
    public void draw(Graphics2D theG2) {
        if (myMapEnabled) {
            // Draw Map
            int width = 200;
            int height = 200;
            int x = SCREEN_WIDTH - width - 50;
            int y = 50;
            theG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            theG2.drawImage(MAZE_MAP, x, y, width, height, null);

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
    public void setMapEnabled(final boolean theEnabled) {
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
