package View;

import Model.Chest;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class ChestPopUp {
    private static final int BORDER = 15;
    private static final Color DARK_PINK = new Color(162, 72, 87);
    private static final Color RED = new Color(139, 0, 0);
    private static final Color BABY_PINK = new Color(245,218,223);
    private static final Color BABY_GREEN = new Color(230,255,239);
    private static final Color GREEN = new Color(95, 133, 117);
    private static final Color DARK_GREEN = new Color(53, 94, 59);

    private static final Color LIGHT_BLUE = new Color(230, 241, 255);
    private Chest myChest;
    private GamePanel myGp;
    private Boolean myItem;
    private JDialog myDialog;
    private JLabel myItemLabel;
    private JLabel myStoneIcon;
    private Player myPlayer;


    public ChestPopUp(Chest theChest, GamePanel theGp, Player thePlayer) {
        myPlayer = thePlayer;
        myChest = theChest;
        myGp = theGp;
        initializeUI(addToDialog());
    }
    public void initializeUI(final JPanel thePanel) {
        myDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(myGp), "Dialog", true);
        myDialog.getContentPane().add(thePanel);
        myDialog.setUndecorated(true);
        myDialog.pack();
        myDialog.setLocationRelativeTo(myGp);
        myDialog.setVisible(true);
    }

    private void showDialog(final JPanel thePanel) {
        myDialog.dispose();
        JDialog dialog = new JDialog(myDialog, "Dialog", true);
        dialog.getContentPane().add(thePanel);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(myDialog);
        dialog.setVisible(true);
    }
    public JPanel addToDialog() {
        JLabel label = new JLabel("OPEN THE CHEST?");

        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.add(label);

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

        noButton.addActionListener(theEvent -> {
            Component comp = (Component) theEvent.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        });
        yesButton.addActionListener(theEvent -> {
                showDialog(itemPanel());
        });
        return panel;
    }
    public JPanel itemPanel() {
        myChest.setLocked(true);
        if (!myChest.isEmptyChest()) {
            myItemLabel = new JLabel("You've obtained a " + myChest.getMyStone() + "!");
            myPlayer.addToBackpack(myChest.getMyStone());
            myItem = true;
        } else {
            myItemLabel = new JLabel("There was nothing inside the chest!");
        }
        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.add(myItemLabel);

        if (myChest.getMyStone() != null) {
            myStoneIcon = new JLabel(new ImageIcon(myChest.getMyStone().getImage()));
        } else {
            myStoneIcon = new JLabel(new ImageIcon("src/res/Miscellaneous_images/mike_wazowski.jpg"));
        }
        JButton continueButton = new JButton("CONTINUE");
        continueButton.setBackground(BABY_GREEN);
        continueButton.setForeground(DARK_GREEN);
        continueButton.setBorder(BorderFactory.createLineBorder(GREEN, 1));
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(20, BORDER, 20,BORDER));
        panel.setLayout(new GridLayout(3,1,10,10));
        panel.setBackground(LIGHT_BLUE);

        panel.add(labelPanel);
        panel.add(myStoneIcon);
        panel.add(continueButton);


        continueButton.addActionListener(theEvent -> {
            Component comp = (Component) theEvent.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        });
        myGp.getHotBar().updateGUI();
        return panel;
    }

}
