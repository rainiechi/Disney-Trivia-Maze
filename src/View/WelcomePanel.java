package View;

import Model.GameSettings;
import SQLite.DBRetriever;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class WelcomePanel extends JPanel {

    private static final int SCREEN_WIDTH = GameSettings.SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT = GameSettings.SCREEN_HEIGHT;

    private static final Color BLUE = new Color(101,139,189);
    private static final Color YELLOW = new Color(249,239,125);
    private static final Font fontForButtons = new Font("Bahnschrift", Font.BOLD, 16);

    private JButton myNewGameButton;
    private JButton myLoadGameButton;
    private Image myBackground;
    private Image myLogoIcon;


    /**
     * Welcome Panel is the opening screen of the game.
     */
    public WelcomePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, GameSettings.SCREEN_HEIGHT));
        ImageIcon backgroundIcon = new ImageIcon("src/res/welcomeScreen/toy_story_background.png");
        myBackground = backgroundIcon.getImage();
        ImageIcon logo = new ImageIcon("src/res/welcomeScreen/logo.png");
        myLogoIcon = logo.getImage();
        setLayout(null);
        setupButtons();

    }


    /**
     * Sets up the two buttons on the welcome screen.
     */
    public void setupButtons() {
        myNewGameButton = new JButton("NEW GAME");
        myLoadGameButton = new JButton("LOAD GAME");

        myNewGameButton.setFont(fontForButtons);
        myLoadGameButton.setFont(fontForButtons);

        myNewGameButton.setBackground(YELLOW);
        myLoadGameButton.setBackground(YELLOW);

        myNewGameButton.setForeground(BLUE);
        myLoadGameButton.setForeground(BLUE);

        myNewGameButton.setBorder(BorderFactory.createLineBorder(BLUE, 3));
        myLoadGameButton.setBorder(BorderFactory.createLineBorder(BLUE, 3));

        myNewGameButton.setBounds(300, 320, 150, 50);
        myLoadGameButton.setBounds(300, 380, 150, 50);

        addButtonListener();

        add(myNewGameButton);
        add(myLoadGameButton);
    }

    /**
     * Adds listeners to the 2 buttons on the welcome screen.
     */
    public void addButtonListener() {
        myNewGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(WelcomePanel.this);
                frame.switchToGamePanel();
            }
        });

        myLoadGameButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Code to load a saved game
            }
        });
    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(myBackground, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        int logoX = (getWidth() - myLogoIcon.getWidth(this)) / 2; // Center the logo horizontally
        int logoY = 100; // Adjust the vertical position of the logo
        g.drawImage(myLogoIcon, logoX, logoY, this);
    }
}