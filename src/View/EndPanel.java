package View;

import Model.GameSettings;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * End panel for either game over or winning scenario.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class EndPanel extends JPanel{
    /** Screen width */
    private static final int SCREEN_WIDTH = GameSettings.SCREEN_WIDTH;
    /** Screen height */
    private static final int SCREEN_HEIGHT = GameSettings.SCREEN_HEIGHT;
    /** RGB for pink */
    private static final Color PINK = new Color(245,218,223);
    /** Timer */
    private Timer myTimer;
    /** Text area to be displayed for end panel */
    private JTextArea myText;
    /** String builder for message shown */
    private StringBuilder mySB;
    /** Index of char position */
    private int myIndex;
    /** Player */
    private Player myPlayer;
    /** Game panel */
    private GamePanel myGp;


    /**
     * The end screen that shows when player reaches the exit door.
     */
    public EndPanel(Player thePlayer, GamePanel theGp) {
        myGp = theGp;
        myPlayer = thePlayer;
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(null);

        myText = new JTextArea();
        myText.setBounds(100, 100, SCREEN_WIDTH, SCREEN_HEIGHT);
        myText.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        myText.setForeground(PINK);
        myText.setBackground(Color.BLACK);
        myText.setLineWrap(true);
        myText.setWrapStyleWord(true);
        myText.setEditable(false);
        add(myText);
        String text = "";
        if (myPlayer.getHealth() == 0) {
            text = "Game over!" +
                    "\nYou've lost all your health points...";
        }
        else if (myPlayer.getHealth() > 0 && myGp.isGameOver()) {
            text = "Game over!" +
                    "\nAll doors to the exit has been locked...";
        } else {
            text = "Wow... you made it!\n\n" +
                    "Congratulations on your magical journey through the Disney Trivia Maze.\n " +
                    "Your knowledge and love for Disney have truly shone through.\n" +
                    "\nNow, go forth and continue creating your own enchanting adventures, \n" +
                    "just like the characters who inspired us all. \n" +
                    "\nMay the magic never fade!" +
                    "\n\n\n\n\n\n\n         Developed by: Amanda Nguyen, Rainie Chi, Karan Sangha";
        }
        mySB = new StringBuilder(text);

        myIndex = 0;

        startTimer();
    }

    /**
     * Used to print out the letters one by one with a delay for the end panel
     * message.
     */
    public void startTimer() {
        JPanel thisP = this;
        myTimer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myIndex < mySB.length()) {
                    myText.setText(mySB.substring(0, myIndex + 1));
                    myIndex++;
                } else {
                    DialogForYesNoAnswer dialog = new DialogForYesNoAnswer("Go back to main screen?", myGp);
                    if (dialog.getMyUserAnswer()) {
                        GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(thisP);
                        frame.switchToWelcomePanel();
                    }
                    myTimer.stop();
                }
            }
        });

        myTimer.start();

    }

    /**
     * Paints end panel to be black
     * @param theG Graphics component
     */
    public void paintComponent(final Graphics theG) {
        super.paintComponent(theG);
        theG.setColor(Color.BLACK);
    }
}
