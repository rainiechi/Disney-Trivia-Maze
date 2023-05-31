package View;

import Model.GameSettings;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EndPanel extends JPanel{
    private static final int SCREEN_WIDTH = GameSettings.SCREEN_WIDTH;
    private static final int SCREEN_HEIGHT = GameSettings.SCREEN_HEIGHT;
    private Timer myTimer;
    private JTextArea myText;
    private StringBuilder mySB;
    private int myIndex;
    private static final Color PINK = new Color(245,218,223);

    public EndPanel() {
        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.BLACK);
        setLayout(null);

        myText = new JTextArea();
        myText.setBounds(100, 100, SCREEN_WIDTH, SCREEN_HEIGHT); // Increased height to accommodate multiple lines
        myText.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        myText.setForeground(PINK);
        myText.setBackground(Color.BLACK);
        myText.setLineWrap(true);
        myText.setWrapStyleWord(true);
        myText.setEditable(false);
        add(myText);

        String text = "Wow... you made it!\n\n" +
                "Congratulations on your magical journey through the Disney Trivia Maze.\n " +
                "Your knowledge and love for Disney have truly shone through.\n" +
                "\nNow, go forth and continue creating your own enchanting adventures, \n" +
                "just like the characters who inspired us all. \n" +
                "\nMay the magic never fade!" +
                "\n\n\n\n\n\n\n         Developed by: Amanda Nguyen, Rainie Chi, Karan Sangha";
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
                    myTimer.stop();
                }
            }
        });

        myTimer.start(); // Start the timer
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
    }
}
