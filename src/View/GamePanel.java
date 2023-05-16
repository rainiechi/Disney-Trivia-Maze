package View;

import Model.GameSettings;
import Model.Maze;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    GameSettings myGS;
    TileManager myTileM;
    Player myPlayer;
    KeyHandler keyH;
    PlayerManager playerManager;
    Maze myMaze;
    Thread myGameThread;
    private int myFPS;

    public GamePanel() {
        myGS = new GameSettings();
        myMaze = new Maze(myGS);
        myPlayer = new Player();
        keyH = new KeyHandler();
        myTileM = new TileManager(this, myGS);
        playerManager = new PlayerManager(this, keyH, myGS, myPlayer);


        myFPS = 60;




        this.setPreferredSize(new Dimension(myGS.getScreenWidth(), myGS.getScreenHeight()));

        // BACKGROUND
        this.setBackground(Color.LIGHT_GRAY);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }
    public void startGameThread() {

        myGameThread = new Thread(this);
        myGameThread.start();
    }
    @Override
    public void run() {
        double drawInterval = 1000000000 / myFPS; // 0.0166 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (myGameThread != null) {

            // Update: Update information such as character position
            update();

            // DRAW: Draw the screen with updated information
            repaint();

            try {

                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public void update() {
        playerManager.update();
    }
    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Tile
       myTileM.draw(g2);
       playerManager.draw(g2);

        g2.dispose();
    }
}
