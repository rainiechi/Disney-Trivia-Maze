package View;

import Model.GameSettings;
import Model.Maze;

import java.awt.*;


public class TileManager {
    private static final int TILE_SIZE = GameSettings.TILE_SIZE;
    private static final int MAX_WORLD_COL = GameSettings.MAX_WORLD_COLUMN;
    private static final int MAX_WORLD_ROW = GameSettings.MAX_WORLD_ROW;
    /** Field for GamePanel */
    private GamePanel myGp;
    /** Field for Maze */
    private Maze myMaze;

    /**
     * Constructor initializes the fields.
     * @param theGp GamePanel object passed in
     * @param theMaze Maze object passed in
     */
    public TileManager(final GamePanel theGp, final Maze theMaze) {
        myGp = theGp;
        myMaze = theMaze;

    }

    /**
     * Method draws the 2D array of tiles from Maze class.
     * @param theG2 Graphics2D object
     */
    public void draw(final Graphics2D theG2) {
    int worldCol = 0;
    int worldRow = 0;

        while (worldCol < MAX_WORLD_COL && worldRow < MAX_WORLD_ROW) {

            int tileNum = myMaze.getMyMapTileNum(worldCol,worldRow);
            // WORLD X AND Y is position on MAP
            int worldX = worldCol * TILE_SIZE;
            int worldY = worldRow * TILE_SIZE;
            // SCREEN X AND Y is position on SCREEN
            int screenX = worldX - myGp.getPlayerManager().getMyWorldX() + myGp.getPlayerManager().getMyX();
            int screenY = worldY - myGp.getPlayerManager().getMyWorldY() + myGp.getPlayerManager().getMyY();

            // IF statement creates boundaries from screen and player position
            // So while the tile is within the boundary, it will be drawn.
            // This means the tiles are only being drawn around the player so it
            // does not slow the program down.
                if (worldX + TILE_SIZE > myGp.getPlayerManager().getMyWorldX() - myGp.getPlayerManager().getMyX() &&
                    worldX - TILE_SIZE < myGp.getPlayerManager().getMyWorldX() + myGp.getPlayerManager().getMyX() &&
                    worldY + TILE_SIZE > myGp.getPlayerManager().getMyWorldY() - myGp.getPlayerManager().getMyY() &&
                    worldY - TILE_SIZE < myGp.getPlayerManager().getMyWorldY() + myGp.getPlayerManager().getMyY()) {

                theG2.drawImage(myMaze.getTile(tileNum).getImage(), screenX, screenY, TILE_SIZE, TILE_SIZE, null);
            }

            worldCol++;

            if (worldCol == MAX_WORLD_COL) {
                worldCol = 0;
                // moves to next row after
                worldRow++;
            }
        }
    }

}
