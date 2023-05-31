package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DialogForYesNoAnswer {
    private static final int BORDER = 15;
    private static final Color DARK_PINK = new Color(162, 72, 87);
    private static final Color RED = new Color(139, 0, 0);
    private static final Color BABY_PINK = new Color(245,218,223);
    private static final Color BABY_GREEN = new Color(230,255,239);
    private static final Color GREEN = new Color(95, 133, 117);
    private static final Color DARK_GREEN = new Color(53, 94, 59);

    private static final Color LIGHT_BLUE = new Color(230, 241, 255);

    private boolean myUserAnswer;
    JDialog myDialog;
    private GamePanel myGp;
    private JLabel myLabel;
    public DialogForYesNoAnswer(String theLabel, GamePanel theGP) {
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

    public boolean getMyUserAnswer() {
        return myUserAnswer;
    }
}