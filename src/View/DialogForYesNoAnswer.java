package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Dialog class for a yes or no answer.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 */
public class DialogForYesNoAnswer {
    /** Border surrounding the dialog */
    private static final int BORDER = 15;
    /** RGB color for dark pink */
    private static final Color DARK_PINK = new Color(162, 72, 87);
    /** RGB color for red */
    private static final Color RED = new Color(139, 0, 0);
    /** RGB color for baby pink */
    private static final Color BABY_PINK = new Color(245,218,223);
    /** RGB color for baby green */
    private static final Color BABY_GREEN = new Color(230,255,239);
    /** RGB color for green  */
    private static final Color GREEN = new Color(95, 133, 117);
    /** RGB color for dark green */
    private static final Color DARK_GREEN = new Color(53, 94, 59);
    /** RGB color for light blue */

    private static final Color LIGHT_BLUE = new Color(230, 241, 255);
    /** Boolean for user answer */
    private boolean myUserAnswer;
    /** JDialog dialog */
    private JDialog myDialog;
    /** Game panel */
    private final GamePanel myGp;
    /** Label to yes or no */
    private JLabel myLabel;

    /**
     * Constructor initializes fields
     * @param theLabel The question that requires yes or no
     * @param theGP Game panel
     */
    public DialogForYesNoAnswer(final String theLabel, final GamePanel theGP) {
        myLabel = new JLabel(theLabel);
        myGp = theGP;
        myUserAnswer = false;
        myDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(myGp), "Dialog", true);
        myDialog.getContentPane().add(addToDialog());
        myDialog.setUndecorated(true);
        myDialog.pack();
        myDialog.setLocationRelativeTo(myGp);
        myDialog.setResizable(false);
        myDialog.setVisible(true);
    }

    /**
     * Add buttons to the dialog.
     * @return JPanel with question and yes or no buttons.
     */
    public JPanel addToDialog() {

        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.add(myLabel);

        JButton yesButton = new JButton("Yes");
        JButton noButton = new JButton("No");

        yesButton.setBackground(BABY_GREEN);
        yesButton.setForeground(DARK_GREEN);
        yesButton.setBorder(BorderFactory.createLineBorder(GREEN, 1));
        noButton.setBackground(BABY_PINK);
        noButton.setForeground(RED);
        noButton.setBorder(BorderFactory.createLineBorder(DARK_PINK, 1));

        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, BORDER, 20,BORDER));
        panel.setLayout(new GridLayout(3,1,10,10));
        panel.setBackground(LIGHT_BLUE);
        panel.add(labelPanel);
        panel.add(yesButton);
        panel.add(noButton);

        // listeners
        yesButton.addActionListener(theEvent -> {
            myUserAnswer = true;
            myDialog.dispose();
        });

        noButton.addActionListener(theEvent -> {
            Component comp = (Component) theEvent.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        });

        return panel;
    }

    /**
     * Getter method for the user's answer.
     * @return myUserAnswer, will be true if user chose yes.
     */
    public boolean getMyUserAnswer() {
        return myUserAnswer;
    }
}