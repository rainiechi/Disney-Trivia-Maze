package View;

import Model.GameSettings;
import Model.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MiniMap {
    private GamePanel myGp;
    private GameSettings myGs;
    private BufferedImage myMazeMap;
    private boolean myMapEnabled;
    private Maze myMaze;

    public MiniMap(GamePanel theGp, GameSettings theGs, Maze theMaze) {
        myGp = theGp;
        myGs = theGs;
        myMaze = theMaze;
        createWorld();
    }

    public void createWorld() {
        int worldMapWidth = myGs.getTileSize() * myGs.getMaxWorldCol();
        int worldMapHeight = myGs.getTileSize() * myGs.getMaxWorldRow();

        myMazeMap = new BufferedImage(worldMapWidth, worldMapHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = myMazeMap.createGraphics();

        int col = 0;
        int row = 0;

        while (col < myGs.getMaxWorldCol() && row < myGs.getMaxWorldRow()) {
            int tileNum = myMaze.getMyMapTileNum(col, row);
            int x = myGs.getTileSize() * col;
            int y = myGs.getTileSize() * row;
            g2.drawImage(myMaze.getTile(tileNum).getImage(), x, y, null);
            col++;
            if (col == myGs.getMaxWorldCol()) {
                col = 0;
                row++;
            }
        }

    }

    public void drawMiniMapScreen(Graphics2D theG2) {
        if (myMapEnabled) {

            // Draw Map
            int width = 200;
            int height = 200;
            int x = myGs.getScreenWidth() - width - 50;
            int y = 50;
            theG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
            theG2.drawImage(myMazeMap, x, y, width, height, null);

            // Draw player
            double scale = (double) (myGs.getTileSize() * myGs.getMaxWorldCol()) / width;
            int playerX = (int) (x + myGp.getPlayerManager().getMyWorldX() / scale) - 15;
            int playerY = (int) (y + myGp.getPlayerManager().getMyWorldY() / scale);
            int playerSize = (myGs.getTileSize()/2);

            theG2.drawImage(myGp.getPlayerManager().getMyHead(), playerX, playerY, playerSize, playerSize, null);
            theG2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
        }
    }

    public void setMapEnabled(boolean enabled) {
        myMapEnabled = enabled;
    }
    public boolean isMapEnabled() {
        return myMapEnabled;
    }
}
