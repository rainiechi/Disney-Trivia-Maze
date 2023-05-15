package View;

import Model.GameSettings;
import Model.Maze;
import Model.Tile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

public class TileManager {
    GamePanel myGp;
    GameSettings myGs;
    Maze myMaze;

    public TileManager(GamePanel gp) {
        this.myGp = gp;
        myGs = new GameSettings();
        myMaze = new Maze(myGs);
    }

    public void draw(Graphics2D g2) {
    int worldCol = 0;
    int worldRow = 0;
    int x = 0;
    int y = 0;

        while (worldCol < myGs.getMaxWorldCol() && worldRow < myGs.getMaxWorldRow()) {
            int tileNum = myMaze.getMyMapTileNum(worldCol,worldRow);
            g2.drawImage(myMaze.getTile(tileNum).getImage(), x, y, myGs.getTileSize(), myGs.getTileSize(), null);
            worldCol++;
            x += myGs.getTileSize();

            if (worldCol == myGs.getMaxWorldCol()) {
                worldCol = 0;
                x = 0;
                worldRow++;
                y += myGs.getTileSize();
             }
        }
    }

}
