package View;

import Model.GameSettings;
import Model.Maze;

import java.awt.*;


public class TileManager {
    private GamePanel myGp;
    private GameSettings myGs;
    private Maze myMaze;

    public TileManager(GamePanel gp, GameSettings gs) {
        this.myGp = gp;
        this.myGs = gs;
        myMaze = new Maze(gs);

    }

    public void draw(Graphics2D g2) {
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

                g2.drawImage(myMaze.getTile(tileNum).getImage(), screenX, screenY, myGs.getTileSize(), myGs.getTileSize(), null);
            }

            worldCol++;

            if (worldCol == myGs.getMaxWorldCol()) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

}
