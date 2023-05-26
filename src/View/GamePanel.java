package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class GamePanel extends JPanel implements Runnable {
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
    private Door myDoor;
    private PopUp myPopUp;
    private Thread myGameThread;
    private QuestionRecord myQuestionRecord;

    private HotbarGUI myHotBar;
    private Backpack myBackPack;

     JLayeredPane layeredPane;
    private JPanel hotbarPanel;

    public GamePanel() {
        myGS = new GameSettings();
        myMaze = new Maze();
        myPlayer = new Player();
        myQuestionRecord = new QuestionRecord();
        myTileM = new TileManager(this, myMaze);
        myMiniMap = new MiniMap(this, myMaze);
        keyH = new KeyHandler(myMiniMap);
        playerManager = new PlayerManager(this, keyH, myPlayer);
        myObj = new ObjectManager[60];
        myAsset = new AssetSetter(myObj);
        myCollisionChecker = new CollisionChecker(this, myMaze, myQuestionRecord);

        myBackPack = new Backpack();
        myHotBar = new HotbarGUI(myBackPack);

        layeredPane = new JLayeredPane();
        layeredPane.setLayout(new BorderLayout());
        hotbarPanel = myHotBar.updateGUI();
        hotbarPanel.setBounds(280, 550, 300, 50);

        layeredPane.setLayer(hotbarPanel,JLayeredPane.PALETTE_LAYER);
        layeredPane.add(hotbarPanel, 0);

        this.setLayout(new BorderLayout());
        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));

        // Background
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

        //hotbarPanel.addKeyListener(myHotBar);

        layeredPane.add(this, BorderLayout.CENTER);
    }

    public void startGameThread() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    public void createPopUp() {
        // myPopUp = new PopUp();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / GameSettings.FPS; // 0.0166 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;

        while (myGameThread != null) {
            // Update: Update information such as character position
            update();

            // Draw: Draw the screen with updated information
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

        // Draw the game elements
        myTileM.draw(g2);
        myMiniMap.drawMiniMapScreen(g2);
        for (int i = 0; i < myObj.length; i++) {
            if (myObj[i] != null) {
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

    public PopUp getPopUp() {
        return myPopUp;
    }
}

