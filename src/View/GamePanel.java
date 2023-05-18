package View;

import Model.GameSettings;
import Model.Maze;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private GameSettings myGS;
    private TileManager myTileM;
    private Player myPlayer;
    private KeyHandler keyH;
    private PlayerManager playerManager;
    private AssetSetter myAsset;
    private ObjectManager[] myObj;
    private CollisionChecker myCollisionChecker;
    private Maze myMaze;
    private MiniMap myMiniMap;
    Thread myGameThread;
    private int myFPS;

    public GamePanel() {
        myGS = new GameSettings();
        myMaze = new Maze(myGS);
        myPlayer = new Player();
        myTileM = new TileManager(this, myGS);
        myMiniMap = new MiniMap(this, myGS, myMaze);
        keyH = new KeyHandler(myMiniMap);
        playerManager = new PlayerManager(this, keyH, myGS, myPlayer);
        myObj = new ObjectManager[45];
        myAsset = new AssetSetter(myGS, myObj);
        myFPS = 60;
        myCollisionChecker = new CollisionChecker(this, myGS, myMaze);




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

       myMiniMap.drawFullMapScreen(g2);

        for (int i = 0; i < myObj.length; i++) {
            if(myObj[i] != null) {
                myObj[i].draw(g2, this);
            }
        }
       playerManager.draw(g2);

        g2.dispose();
    }
    public PlayerManager getPlayerManager() {
        return playerManager;
    }
    public Maze getMaze() {
        return myMaze;
    }
    public ObjectManager[] getObj() {
        return myObj;
    }
    public CollisionChecker getCC() {
        return myCollisionChecker;
    }
}
