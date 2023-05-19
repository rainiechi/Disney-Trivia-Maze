package View;

import Model.GameSettings;
import Model.Maze;

import java.awt.*;
import java.awt.image.BufferedImage;

public class MiniMap {
    /** private GamePanel object field */
    private GamePanel myGp;
    /** private GameSettings object field */
    private GameSettings myGs;
    /** Private field for image */
    private BufferedImage myMazeMap;
    /** Private boolean field if map is enabled */
    private boolean myMapEnabled;
    /** private Maze object field */
    private Maze myMaze;

    /**
     * Constructor initializes the fields.
     * @param theGp GamePanel passed to constructor
     * @param theGs GameSettings passed to constructor
     * @param theMaze Maze passed to constructor
     */
    public MiniMap(GamePanel theGp, GameSettings theGs, Maze theMaze) {
        myGp = theGp;
        myGs = theGs;
        myMaze = theMaze;
        createWorld();
    }

    /**
     * Method draws the map.
     */
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

    /**
     * Method draws the maze and player scaled down so it is a mini map.
     * @param theG2 graphics2D object
     */
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
