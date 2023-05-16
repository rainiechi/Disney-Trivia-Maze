package View;

import Model.GameSettings;
import Model.Maze;
import Model.Player;

import java.awt.*;


public class TileManager {
    GamePanel myGp;
    GameSettings myGs;
    Maze myMaze;
    PlayerManager myPlayerM;

    public TileManager(GamePanel gp, GameSettings gs) {
        this.myGp = gp;
        this.myGs = gs;
//        this.myPlayerM = player;
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
            int screenX = worldX - myGp.playerManager.getMyWorldX() + myGp.playerManager.getMyX();
            int screenY = worldY - myGp.playerManager.getMyWorldY() + myGp.playerManager.getMyY();

            // IF statement creates boundaries from screen and player position
            // So while the tile is within the boundary, it will be drawn.
            // This means the tiles are only being drawn around the player so it
            // does not slow the program down.
                if (worldX + myGs.getTileSize() > myGp.playerManager.getMyWorldX() - myGp.playerManager.getMyX() &&
                    worldX - myGs.getTileSize() < myGp.playerManager.getMyWorldX() + myGp.playerManager.getMyX() &&
                    worldY + myGs.getTileSize() > myGp.playerManager.getMyWorldY() - myGp.playerManager.getMyY() &&
                    worldY - myGs.getTileSize() < myGp.playerManager.getMyWorldY() + myGp.playerManager.getMyY()) {

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
