package View;

import Model.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class GamePanel extends JPanel implements Runnable{
    private final static Maze MAZE = new Maze();
    private transient TileManager myTileM;
    private transient AssetSetter myAssetSetter;
    private CollisionChecker myCollisionChecker;
    private transient Thread myGameThread;
    private Game myGame;

    public GamePanel() {
        myTileM = new TileManager(this);
        setMyGame(new Game(this));
        myAssetSetter = new AssetSetter(myGame.getMyObjManagers());

        // BACKGROUND
        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
    }


    public void setMyGame(final Game theGame) {
        myGame = theGame;
        myCollisionChecker = new CollisionChecker(this, myGame.getMyQuestionRecord());
        addKeyListener(myGame.getMyKeyHandler());
        this.setFocusable(true);
    }

    public void startGameThread() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }



    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("game_state.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myGame);
            out.close();
            fileOut.close();
            System.out.println("Game state saved successfully.");
        } catch (Exception e) {
            System.out.println("Error occurred while saving the game state: " + e.getMessage());
        }
    }

    public void loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("game_state.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game loadedGame = (Game) in.readObject();
            in.close();
            fileIn.close();

            setMyGame(loadedGame);
            myGame.getMyPlayerManager().setPlayerImage(); //set up images again because they're transient

            System.out.println("Game state loaded successfully.");
            repaint();
        } catch (Exception e) {
            System.out.println("Error occurred while loading the game state: " + e.getMessage());
        }
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
        return MAZE;
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