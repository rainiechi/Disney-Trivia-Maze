package View;

import Model.*;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    private TileManager myTileM;
    private AssetSetter myAssetSetter;
    private CollisionChecker myCollisionChecker;
    private Maze myMaze;
    private transient Thread myGameThread;
    //private Player myPlayer;
    //private KeyHandler myKeyHandler;
    //private PlayerManager playerManager;
    //private ObjectManager[] myObjManagers;
    //private MiniMap myMiniMap;
    //private QuestionRecord myQuestionRecord;

    private Game myGame;

    public GamePanel() {
        myMaze = new Maze();
        myGame = new Game(this);

        //myPlayer = new Player();
        //myQuestionRecord = new QuestionRecord();
        myTileM = new TileManager(this);
        //myMiniMap = new MiniMap(this);
        //myKeyHandler = new KeyHandler(myMiniMap);
        //playerManager = new PlayerManager(this, myKeyHandler, myPlayer);
        //myObjManagers = new ObjectManager[60];
        myAssetSetter = new AssetSetter(myGame.getMyObjManagers());
        myCollisionChecker = new CollisionChecker(this, myGame.getMyQuestionRecord());
        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));

        // BACKGROUND
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(myGame.getMyKeyHandler());
        this.setFocusable(true);
    }
    public void startGameThread() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / GameSettings.FPS; // 0.0166 seconds
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
        myGame.getMyPlayerManager().update();
    }


    public Game getMyGame() {
        return myGame;
    }

    public void setMyGame(final Game theGame) {
        myGame = theGame;
        myAssetSetter = new AssetSetter(myGame.getMyObjManagers());
        myCollisionChecker = new CollisionChecker(this, myGame.getMyQuestionRecord());
        repaint();
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        // Tile
        myTileM.draw(g2);

        myGame.getMyMiniMap().drawMiniMapScreen(g2);

        for (int i = 0; i < myGame.getMyObjManagers().length; i++) {
            if(myGame.getObjManager(i) != null) {
                myGame.getObjManager(i).draw(g2, this);
            }
        }
        myGame.getMyPlayerManager().draw(g2);

        g2.dispose();
    }
    public PlayerManager getPlayerManager() {
        return myGame.getMyPlayerManager();
    }
    public Maze getMaze() {
        return myMaze;
    }
    public ObjectManager[] getObj() {
        return myGame.getMyObjManagers();
    }

    public ObjectManager getObjManager(final int theIndex) {
        return myGame.getObjManager(theIndex);
    }

    public void deleteObjManager(final int theIndex) {
        myGame.deleteObjManager(theIndex);
    }

    public CollisionChecker getCC() {
        return myCollisionChecker;
    }

}