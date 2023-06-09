package View;

import Model.Chest;
import Model.Player;

import javax.swing.*;
import java.awt.*;

/**
 * GUI Dialog for Chest Object when Player touches Chest.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @verison 6/5/23
 */
public class ChestPopUp {
    /** Border size surrounding dialog */
    private static final int BORDER = 15;
    /** RGB for baby green color */
    private static final Color BABY_GREEN = new Color(230,255,239);
    /** RGB for green color */
    private static final Color GREEN = new Color(95, 133, 117);
    /** RGB for dark green color */
    private static final Color DARK_GREEN = new Color(53, 94, 59);
    /** RGB for light blue color */
    private static final Color LIGHT_BLUE = new Color(230, 241, 255);
    /** Chest object */
    private final Chest myChest;
    /** Game panel */
    private final GamePanel myGp;
    /** Item label */
    private JLabel myItemLabel;
    /** Stone icon */
    private JLabel myStoneIcon;
    /** Player object */
    private final Player myPlayer;

    /**
     * Constructor initializes the fields.
     * @param theChest chest object
     * @param theGp game panel object
     * @param thePlayer player object
     */
    public ChestPopUp(final Chest theChest, final GamePanel theGp, final Player thePlayer) {
        myPlayer = thePlayer;
        myChest = theChest;
        myGp = theGp;
        DialogForYesNoAnswer d = new DialogForYesNoAnswer("OPEN THE CHEST?", myGp);
        // If user clicks yes, it will show a dialog of whats inside chest
        if (d.getMyUserAnswer()) {
            showDialog(itemPanel());
        }
    }

    /**
     * Method for dialog settings and displaying if user clicks yes.
     * @param thePanel panel to be displayed
     */
    private void showDialog(final JPanel thePanel) {
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(myGp), "Dialog", true);
        dialog.getContentPane().add(thePanel);
        dialog.setUndecorated(true);
        dialog.pack();
        dialog.setLocationRelativeTo(SwingUtilities.getWindowAncestor(myGp));
        dialog.setVisible(true);
    }

    /**
     * Method itemPanel checks if chest is empty or has a stone. If chest has a stone, the icon
     * is set to the stone object to be displayed as the chest inventory. Else, the icon is set
     * to Mike Wazowski.
     * @return the panel representing chest inventory.
     */
    public JPanel itemPanel() {
        myChest.setLocked(true);
        if (!myChest.isEmptyChest()) {
            myItemLabel = new JLabel("You've obtained a " + myChest.getMyStone() + "!");
            myStoneIcon = new JLabel(new ImageIcon(myChest.getMyStone().getImage()));
            myPlayer.takeStone(myChest);
            myGp.getHotBar().updateGUI(myGp.getMyGame().getMyPlayer(),myGp);
            myPlayer.getBackpack().displayCurrInventory();
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


        continueButton.addActionListener(theEvent -> {
            Component comp = (Component) theEvent.getSource();
            Window win = SwingUtilities.getWindowAncestor(comp);
            win.dispose();
        });
        return panel;
    }

}
