package View;

import Model.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.io.*;

/**
 * GamePanel contains the necessary components needed for the game to run.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class GamePanel extends JPanel implements Runnable {
    /** TileManager object */
    private final transient TileManager myTileM;
    /** AssetSetter object */
    private final transient AssetSetter myAssetSetter;
    /** Game thread to run */
    private transient Thread myGameThread;
    /** Game object */
    private Game myGame;
    /** SoundManager object */
    private transient final SoundManager mySound;
    /** Hot bar object to represent backpack */
    private final HotbarGUI myHotBar;
    /** Layered pane */
    private JLayeredPane myLayeredPane;
    /** PlayerHealth represents the player's current health */
    private transient PlayerHealth myPlayerHealth;
    /** Boolean if game is over */
    private boolean myGameOver;

    /**
     * Constructor to initialize necessary fields.
     */
    public GamePanel() {
        myTileM = new TileManager(this);
        setMyGame(new Game(this));
        mySound = new SoundManager();
        myAssetSetter = new AssetSetter(myGame.getMyObjManagers());
        myGameOver = false;
        // BACKGROUND
        this.setPreferredSize(new Dimension(GameSettings.SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        this.setFocusable(true); // Enable keyboard focus on

        //myBackPack = new Backpack();
        myHotBar = new HotbarGUI();
        myLayeredPane = new JLayeredPane();

        myLayeredPane = new JLayeredPane();
        myLayeredPane.setLayout(new BorderLayout());
        //JPanel hotbarPanel = myHotBar.updateGUI();

        myHotBar.setBounds(280, 550, 300, 50);
        myHotBar.updateGUI(getMyGame().getMyPlayer(),this);

        myLayeredPane.setLayer(myHotBar,JLayeredPane.PALETTE_LAYER);
        myLayeredPane.add(myHotBar,0);

        myLayeredPane.add(this);

    }


    /**
     * Sets the game (either a saved game or new game).
     * @param theGame the game to be set
     */
    private void setMyGame(final Game theGame) {
        System.out.println("1");
        myGame = theGame;
        addKeyListener(myGame.getMyKeyHandler());
        myPlayerHealth = new PlayerHealth(myGame.getMyPlayer());
        this.setFocusable(true);
    }

    /**
     * Starts the game thread.
     */
    public void startGameThread() {
        myGameThread = new Thread(this);
        myGameThread.start();
    }

    /**
     * Returns the layered panel with the hotbar.
     * @return layeredpane
     */
    public JLayeredPane getMyLayeredPane() {
        return myLayeredPane;
    }


    /**
     * Saves the current game.
     */
    public void saveGame() {
        try {
            FileOutputStream fileOut = new FileOutputStream("game_state.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myGame);
            out.close();
            fileOut.close();
            System.out.println("Game state saved successfully.");
            showDialog(new SaveLoadPanel("saved"));
        } catch (Exception e) {
            System.out.println("Error occurred while saving the game state: " + e.getMessage());
        }
    }

    /**
     * Loads a saved game.
     * @return whether there was a saved game file.
     */
    public boolean loadGame() {
        try {
            FileInputStream fileIn = new FileInputStream("game_state.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Game loadedGame = (Game) in.readObject();
            in.close();
            fileIn.close();

            setMyGame(loadedGame);
            myGame.getMyPlayerManager().setPlayerImage(); //set up images again because they're transient
            myGame.setMyCollisionChecker(this); //to make sure PopUp has the correct Frame owner
            System.out.println("Game state loaded successfully.");
            showDialog(new SaveLoadPanel("loaded"));
            myHotBar.updateGUI(getMyGame().getMyPlayer(),this);

            repaint();
            myGame.getMyPlayer().getBackpack().displayCurrInventory(); //for testing
            System.out.println(myGame.getMyPlayer().getHealth());
            return true;
        } catch (Exception e) {
            showDialog(new SaveLoadPanel("NoSavedFile"));
            System.out.println("Error occurred while loading the game state: " + e.getMessage());
            return false;
        }
    }

    /**
     * Deletes the current saved game file.
     */
    public void deleteSavedGame() {
        try {
            File savedFile = new File("game_state.ser");
            if (savedFile.exists()) {
                if (savedFile.delete()) {
                    System.out.println("Saved game file deleted successfully.");
                } else {
                    System.out.println("Failed to delete the saved game file.");
                }
            } else {
                System.out.println("No saved game file exists.");
            }
        } catch (Exception e) {
            System.out.println("Error occurred while deleting the saved game file: " + e.getMessage());
        }
    }

    /**
     * Runs the application at 60 FPS while drawing and updating the player
     * position on screen.
     */
    @Override
    public void run() {
        double drawInterval = (double) 1000000000 / GameSettings.FPS; // 0.0166 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        long lastUpdateTime = System.currentTimeMillis();

        while (myGameThread != null) {
            // Update: Update information such as character position
            update();

            // DRAW: Draw the screen with updated information
            repaint();

            long currentTime = System.currentTimeMillis();
            if (currentTime - lastUpdateTime >= 3000) {
                myHotBar.updateGUI(myGame.getMyPlayer(),this); // Call updateGUI() every second
                lastUpdateTime = currentTime;
            }

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

    /**
     * Updates the player's position.
     */
    public void update() {
        myGame.getMyPlayerManager().update();
    }

    /**
     * Plays music for the game.
     * @param theIndex index of the audio in SoundManager.
     */
    public void playMusic(final int theIndex) {
        mySound.setFile(theIndex);
        mySound.play();
        mySound.loop();
    }

    /**
     * Returns the current Game.
     * @return current Game
     */
    public Game getMyGame() {
        return myGame;
    }

    /**
     * Draws the world map of tiles, player, and objects in game.
     * @param theG Graphics component
     */
    public void paintComponent(Graphics theG) {

        super.paintComponent(theG);

        Graphics2D g2 = (Graphics2D) theG;

        // Tile
        myTileM.draw(g2);

        myGame.getMyMiniMap().draw(g2);

        for (int i = 0; i < myGame.getMyObjManagers().length; i++) {
            if(myGame.getObjManager(i) != null) {
                myGame.getObjManager(i).draw(g2, this);
            }
        }
        myGame.getMyPlayerManager().draw(g2);
        myPlayerHealth.draw(g2);


        g2.dispose();
    }

    /**
     * Getter method for PlayerManager.
     * @return the player manager
     */
    public PlayerManager getPlayerManager() {
        return myGame.getMyPlayerManager();
    }

    /**
     * Getter method for ObjectManager contained in Game.
     * @return the ObjectManager
     */
    public ObjectManager[] getObjManagers() {
        return myGame.getMyObjManagers();
    }

    /**
     * Getter method for the object at index in the array of ObjectManager.
     * @param theIndex index of object
     * @return object at index
     */
    public ObjectManager getObjManager(final int theIndex) {
        return myGame.getObjManager(theIndex);
    }

    /**
     * Deletes the object at index in the array of ObjectManager.
     * @param theIndex object at index to be deleted.
     */
    public void deleteObjManager(final int theIndex) {
        myGame.deleteObjManager(theIndex);
    }

    /**
     * Getter method for CollisionChecker.
     * @return CollisionChecker object.
     */
    public CollisionChecker getCC() {
        return myGame.getMyCollisionChecker();
    }

    /**
     * Getter method for game object.
     * @return game object
     */
    public Game getGame() {
        return myGame;
    }

    /**
     * Getter method for HotBar object.
     * @return HotBar object.
     */
    public HotbarGUI getHotBar() {
        return myHotBar;
    }


    /**
     * Creates and show JDialog
     * @param thePanel the panel to be contained by the JDialog
     */
    private void showDialog(final JPanel thePanel) {
        GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(GamePanel.this);
        JDialog dialog = new JDialog(frame, "hi", true);
        dialog.getContentPane().add(thePanel);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(this);
        dialog.setVisible(true);
    }


    /**
     * The popup screen for game saved and game loaded.
     */
    class SaveLoadPanel extends JPanel {
        private static final int BORDER = 15;

        private static final Color PERIWINKLE = new Color(220, 215, 247);
        private static final Color DARK_BLUE = new Color(70, 130, 180);
        private static final Color PASTEL_BLUE = new Color(173, 216, 230);

        public SaveLoadPanel(final String theString) { //true for save false for load
            JButton continueButton = new JButton("CONTINUE");
            JLabel resultLabel1 = new JLabel();
            setBackground(PERIWINKLE);

            continueButton.setForeground(DARK_BLUE);
            continueButton.setBackground(PASTEL_BLUE);
            continueButton.setBorder(BorderFactory.createLineBorder(DARK_BLUE, 1));

            if (theString.equalsIgnoreCase("saved")) {
                resultLabel1 = new JLabel("PROGRESS SAVED");
                resultLabel1.setForeground(DARK_BLUE);
            } else if (theString.equalsIgnoreCase("loaded")){
                resultLabel1 = new JLabel("PROGRESS LOADED");
                resultLabel1.setForeground(DARK_BLUE);
            } else if (theString.equalsIgnoreCase("NoSavedFile")) {
                resultLabel1 = new JLabel("NO SAVED FILE FOUND");
                resultLabel1.setForeground(DARK_BLUE);
            }


            JPanel resultPanel1 = new JPanel();
            resultPanel1.setOpaque(false);
            resultPanel1.add(resultLabel1);


            setBorder(BorderFactory.createEmptyBorder(BORDER, BORDER, BORDER,BORDER));
            setLayout(new GridLayout(2, 1, 10, 10));
            add(resultPanel1);
            add(continueButton);

            //disposes the window and resumes game
            continueButton.addActionListener(theEvent -> {
                Component comp = (Component) theEvent.getSource();
                Window win = SwingUtilities.getWindowAncestor(comp);
                win.dispose();
            });

        }

    }

    /**
     * Setter method for myGameOver.
     * @param theGame boolean to set.
     */
    public void setGameOver(boolean theGame) {
        myGameOver = theGame;
    }

    /**
     * Getter method for myGameOver.
     * @return true if game over, false otherwise.
     */
    public boolean isGameOver() {
        return myGameOver;
    }
}
