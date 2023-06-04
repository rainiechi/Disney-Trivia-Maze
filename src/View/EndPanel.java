package View;

import Model.GameSettings;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends JPanel{
    private static final int SCREEN_WIDTH = GameSettings.SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT = GameSettings.SCREEN_HEIGHT;
    private static final Color PINK = new Color(245,218,223);
    private Timer myTimer;
    private JTextArea myText;
    private StringBuilder mySB;
    private int myIndex;
    private Player myPlayer;
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

    public void startTimer() {
        myTimer = new Timer(25, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (myIndex < mySB.length()) {
                    myText.setText(mySB.substring(0, myIndex + 1));
                    myIndex++;
                } else {
                    DialogForYesNoAnswer dialog = new DialogForYesNoAnswer("Go back to main screen?", myGp);
                    if (dialog.getMyUserAnswer()) {
                        GameFrame frame = (GameFrame) SwingUtilities.getWindowAncestor(myGp);
                        frame.switchToWelcomePanel();
                    }
                    myTimer.stop();
                }
            }
        });

        myTimer.start();

    }

    public void paintComponent(final Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
    }
}
