package View;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private final int myOriginalTileSize;
    private final int myScale;
    private final int myTileSize;
    private final int myMaxScreenCol;
    private final int myMaxScreenRow;
    private final int myScreenWidth; // 1024 pixels
    private final int myScreenHeight; // 768 pixels
    private final int myMaxWorldCol;
    private final int myMaxWorldRow;
    private final int myFPS;

    TileManager myTileM;

    Thread myGameThread;

    public GamePanel() {
        myOriginalTileSize = 32; // 32x32 tile
        myScale = 2;
        myTileSize = myOriginalTileSize * myScale; // 64x64 tile
        myMaxScreenCol = 16; // ratio is 4x3
        myMaxScreenRow = 12;
        myScreenWidth = 48 * myMaxScreenCol;
        myScreenHeight = 48 * myMaxScreenRow;
        myMaxWorldCol = 52;
        myMaxWorldRow = 48;
        myFPS = 60;
        myTileM = new TileManager(this);
        this.setPreferredSize(new Dimension(myScreenWidth, myScreenHeight));
        // BACKGROUND
        this.setBackground(Color.LIGHT_GRAY);
    }
    public void startGameThread() {

        myGameThread = new Thread(this);
        myGameThread.start();
    }
    @Override
    public void run() {
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Tile
       myTileM.draw(g2);

        g2.dispose();
    }
    public int getTileSize() {
        return myTileSize;
    }

    public int getMaxScreenCol() {
        return myMaxScreenCol;
    }

    public int getMaxScreenRow() {
        return myMaxScreenRow;
    }

    public int getScreenWidth() {
        return myScreenWidth;
    }

    public int getScreenHeight() {
        return myScreenHeight;
    }

    public int getMaxWorldRow() {
        return myMaxWorldRow;
    }

    public int getMaxWorldCol() {
        return myMaxWorldCol;
    }
}
