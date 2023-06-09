package View;

import Model.GameSettings;

import javax.swing.*;
import java.awt.*;

public class WelcomePanel extends JPanel {

    /**
     The width of the screen.
     */
    private static final int SCREEN_WIDTH = GameSettings.SCREEN_WIDTH;
    /**
     The height of the screen.
     */
    private static final int SCREEN_HEIGHT = GameSettings.SCREEN_HEIGHT;
    /**

     The color blue.
     */
    private static final Color BLUE = new Color(101,139,189);
    /**
     The color blue.
     */
    private static final Color YELLOW = new Color(249,239,125);
    /**
     The font used for buttons.
     */
    private static final Font FONT_FOR_BUTTONS = new Font("Bahnschrift", Font.BOLD, 16);
    /**
     The button for starting a new game.
     */
    private JButton myNewGameButton;
    /**
     The button for loading a game.
     */
    private JButton myLoadGameButton;
    /**
     The background image.
     */
    private final Image myBackground;
    /**
     The logo icon image.
     */
    private final Image myLogoIcon;


    /**
     * Welcome Panel is the opening screen of the game.
     */
    public WelcomePanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
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
    private void setupButtons() {
        myNewGameButton = new JButton("NEW GAME");
        myLoadGameButton = new JButton("LOAD GAME");

        myNewGameButton.setFont(FONT_FOR_BUTTONS);
        myLoadGameButton.setFont(FONT_FOR_BUTTONS);

        myNewGameButton.setBackground(YELLOW);
        myLoadGameButton.setBackground(YELLOW);

        myNewGameButton.setForeground(BLUE);
        myLoadGameButton.setForeground(BLUE);

        myNewGameButton.setBorder(BorderFactory.createLineBorder(BLUE, 3));
        myLoadGameButton.setBorder(BorderFactory.createLineBorder(BLUE, 3));

        myNewGameButton.setBounds(350, 320, 150, 50);
        myLoadGameButton.setBounds(350, 380, 150, 50);

        addButtonListener();

        add(myNewGameButton);
        add(myLoadGameButton);
    }

    /**
     * Adds listeners to the 2 buttons on the welcome screen.
     */
    public void addButtonListener() {
        myNewGameButton.addActionListener(e -> {
            GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(WelcomePanel.this);
            frame.switchToGamePanel(new GamePanel());
        });

        myLoadGameButton.addActionListener(e -> {
            GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(WelcomePanel.this);
            if (frame.getMyGamePanel().loadGame()) {
                frame.switchToGamePanel(frame.getMyGamePanel());
            }
        });
    }

    /**

     Overrides the paintComponent method to customize the appearance of the component.
     @param g The Graphics object used for painting.
     */
    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.drawImage(myBackground, 0, 0, getWidth(), getHeight(), this);
        g.setColor(Color.BLACK);
        int logoX = (getWidth() - myLogoIcon.getWidth(this)) / 2; // Center the logo horizontally
        int logoY = 100; // Adjust the vertical position of the logo
        g.drawImage(myLogoIcon, logoX, logoY, this);
    }
}