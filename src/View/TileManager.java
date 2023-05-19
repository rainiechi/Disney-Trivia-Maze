package View;

import Model.GameSettings;
import Model.Maze;

import java.awt.*;


public class TileManager {
    /** Field for GamePanel */
    private GamePanel myGp;
    /** Field for GameSettings */
    private GameSettings myGs;
    /** Field for Maze */
    private Maze myMaze;

    /**
     * Constructor initializes the fields.
     * @param theGp GamePanel object passed in
     * @param theGs GameSettings object passed in
     * @param theMaze Maze object passed in
     */
    public TileManager(GamePanel theGp, GameSettings theGs, Maze theMaze) {
        this.myGp = theGp;
        this.myGs = theGs;
        myMaze = theMaze;

    }

    /**
     * Method draws the 2D array of tiles from Maze class.
     * @param theG2 Graphics2D object
     */
    public void draw(Graphics2D theG2) {
    int worldCol = 0;
    int worldRow = 0;

        while (worldCol < myGs.getMaxWorldCol() && worldRow < myGs.getMaxWorldRow()) {

            int tileNum = myMaze.getMyMapTileNum(worldCol,worldRow);
            // WORLD X AND Y is position on MAP
            int worldX = worldCol * myGs.getTileSize();
            int worldY = worldRow * myGs.getTileSize();
            // SCREEN X AND Y is position on SCREEN
            int screenX = worldX - myGp.getPlayerManager().getMyWorldX() + myGp.getPlayerManager().getMyX();
            int screenY = worldY - myGp.getPlayerManager().getMyWorldY() + myGp.getPlayerManager().getMyY();

            // IF statement creates boundaries from screen and player position
            // So while the tile is within the boundary, it will be drawn.
            // This means the tiles are only being drawn around the player so it
            // does not slow the program down.
                if (worldX + myGs.getTileSize() > myGp.getPlayerManager().getMyWorldX() - myGp.getPlayerManager().getMyX() &&
                    worldX - myGs.getTileSize() < myGp.getPlayerManager().getMyWorldX() + myGp.getPlayerManager().getMyX() &&
                    worldY + myGs.getTileSize() > myGp.getPlayerManager().getMyWorldY() - myGp.getPlayerManager().getMyY() &&
                    worldY - myGs.getTileSize() < myGp.getPlayerManager().getMyWorldY() + myGp.getPlayerManager().getMyY()) {

                theG2.drawImage(myMaze.getTile(tileNum).getImage(), screenX, screenY, myGs.getTileSize(), myGs.getTileSize(), null);
            }

            worldCol++;

            if (worldCol == myGs.getMaxWorldCol()) {
                worldCol = 0;
                // moves to next row after
                worldRow++;
            }
        }
    }

}
