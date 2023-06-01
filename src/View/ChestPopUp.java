package View;

import Model.Chest;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class ChestPopUp {
    private static final int BORDER = 15;
    private static final Color BABY_GREEN = new Color(230,255,239);
    private static final Color GREEN = new Color(95, 133, 117);
    private static final Color DARK_GREEN = new Color(53, 94, 59);

    private static final Color LIGHT_BLUE = new Color(230, 241, 255);
    private Chest myChest;
    private GamePanel myGp;
    private Boolean myItem;
    private JLabel myItemLabel;
    private JLabel myStoneIcon;
    private Player myPlayer;


    public ChestPopUp(Chest theChest, GamePanel theGp, Player thePlayer) {
        myPlayer = thePlayer;
        myChest = theChest;
        myGp = theGp;
        DialogForYesNoAnswer d = new DialogForYesNoAnswer("OPEN THE CHEST?", myGp);
        if (d.getMyUserAnswer()) {
            showDialog(itemPanel());
        }
    }

    private void showDialog(final JPanel thePanel) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(myGp), "Dialog", true);
        dialog.getContentPane().add(thePanel);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(myGp));
        dialog.setVisible(true);
    }
    public JPanel itemPanel() {
        myChest.setLocked(true);
        if (!myChest.isEmptyChest()) {
            myItemLabel = new JLabel("You've obtained a " + myChest.getMyStone() + "!");
            myStoneIcon = new JLabel(new ImageIcon(myChest.getMyStone().getImage()));
            myPlayer.takeStone(myChest);
            myPlayer.getBackpack().displayCurrInventory();
            myItem = true;
        } else {
            myItemLabel = new JLabel("There was nothing inside the chest!");
            myPlayer.getBackpack().displayCurrInventory();
            myStoneIcon = new JLabel(new ImageIcon("src/res/Miscellaneous_images/mike_wazowski.jpg"));
        }
        JPanel labelPanel = new JPanel();
        labelPanel.setOpaque(false);
        labelPanel.add(myItemLabel);

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

        //myGp.getHotBar().updateGUI();

        continueButton.addActionListener(theEvent -> {
            Component comp = (Component) theEvent.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        });
        return panel;
    }

}
