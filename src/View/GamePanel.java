package View;

import Model.GameSettings;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{
    GameSettings myGS;
    TileManager myTileM;

    Thread myGameThread;

    public GamePanel() {
        myGS = new GameSettings();
        myTileM = new TileManager(this);
        this.setPreferredSize(new Dimension(myGS.getScreenWidth(), myGS.getScreenHeight()));
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
}
