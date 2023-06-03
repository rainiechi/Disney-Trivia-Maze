package View;

import Model.Player;
import Model.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HotbarGUI extends JPanel {
    private static final int HOTBAR_SIZE = 6;

    private JButton[] slots;
    private int selectedSlotIndex;


    public HotbarGUI() {
        slots = new JButton[HOTBAR_SIZE];
        selectedSlotIndex = 0;
        setLayout(new FlowLayout()); // Set layout to FlowLayout
    }

    public void updateGUI(final Player thePlayer, final GamePanel theGP) {
        removeAll();

        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for (int i = 0; i < HOTBAR_SIZE; i++) {
            JButton slotButton = new JButton();
            slotButton.setPreferredSize(new Dimension(50, 50));
            slotButton.setBackground(new Color(234, 210, 182));

            slotButton.setLayout(new GridBagLayout());
            if (thePlayer.getBackpack().getStone(i) != null) {
                slotButton.setIcon(new ImageIcon(thePlayer.getBackpack().getStone(i).getImage()));
                slotButton.setToolTipText(thePlayer.getBackpack().getStone(i).getDescription());
            }

            slots[i] = slotButton;
            add(slotButton);

            final int index = i;

            slotButton.addMouseListener(new MouseAdapter() {
                private final int DOUBLE_CLICK_DELAY = 300; // Time threshold for a double-click in milliseconds
                private boolean clickedOnce = false;

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (SwingUtilities.isLeftMouseButton(e)) {
                        if (e.getClickCount() == 2) {
                            try {
                                selectedSlotIndex=index;
                                askToUseStone(thePlayer, theGP);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        }
                        else {
                            clickedOnce = true;
                            selectedSlotIndex=index;
                            theGP.requestFocusInWindow();
                            Timer timer = new Timer(DOUBLE_CLICK_DELAY, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (clickedOnce) {
                                    }
                                    clickedOnce = false;
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                            theGP.requestFocusInWindow();
                        }
                    }
                }
            });

            revalidate();
            repaint();
        }
    }


    private void askToUseStone(final Player thePlayer, final GamePanel theGP) throws IOException {
        Stone stone = thePlayer.getBackpack().getStone(selectedSlotIndex);
        Color Royal_blue = new Color(83,157,255);
        Color peach = new Color(238,164,127);

        // Create a custom JDialog
        JDialog dialog = new JDialog();
        dialog.setTitle("Information Pane");
        dialog.setModal(true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        // Set the UIManager properties for the JDialog
        UIManager.put("OptionPane.background", Royal_blue);
        UIManager.put("Panel.background", Royal_blue);
        UIManager.put("OptionPane.messageForeground", Color.WHITE);
        UIManager.put("Button.background", peach);
        UIManager.put("Button.foreground", Color.WHITE);


        if (stone != null) {

            if (stone.getStoneName().equals("Reality Stone")) {
                if(theGP.getCC().getMyPopUp().getMyDialog().isVisible()) {
                    DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer("Would you like to use this item?", theGP);
                    if (yesNoDialog.getMyUserAnswer()) {
                        theGP.getCC().getMyPopUp().disableWrongAnswerButton(3);
                        thePlayer.useStone(stone);
                    }
                }
                else {
                    // Show a colorful JOptionPane with a message and OK button
                    JOptionPane.showMessageDialog(dialog, "You can only use this Stone when answering trivia",
                            "Information Pane", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
            else if (stone.getStoneName().equals("Mind Stone")) {
                if(theGP.getCC().getMyPopUp().getMyDialog().isVisible()) {
                    DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer("Would you like to use this item?", theGP);
                    if (yesNoDialog.getMyUserAnswer()) {
                        theGP.getCC().getMyPopUp().disableWrongAnswerButton(1);
                        thePlayer.useStone(stone);
                    }
                }
                else {
                    // Show a colorful JOptionPane with a message and OK button
                    JOptionPane.showMessageDialog(dialog, "You can only use this Stone when answering trivia",
                            "Information Pane", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
            else if (stone.getStoneName().equals("Time Stone")) {
                if(!theGP.getCC().getMyPopUp().getMyDialog().isVisible()) {
                    DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer("Would you like to use this item?", theGP);
                    if (yesNoDialog.getMyUserAnswer()) {
                        thePlayer.useStone(stone);
                    }
                }
                else {
                    // Show a colorful JOptionPane with a message and OK button
                    JOptionPane.showMessageDialog(dialog, "You cann't use this Stone when answering trivia",
                            "Information Pane", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
            else if (stone.getStoneName().equals("Power Stone")) {
                if(!theGP.getCC().getMyPopUp().getMyDialog().isVisible()) {
                    DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer("Would you like to use this item?", theGP);
                    if (yesNoDialog.getMyUserAnswer()) {
                        thePlayer.useStone(stone);
                    }
                }
                else {
                    // Show a colorful JOptionPane with a message and OK button
                    JOptionPane.showMessageDialog(dialog, "You cann't use this Stone when answering trivia",
                            "Information Pane", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }
            else if (stone.getStoneName().equals("Soul Stone")) {
                JOptionPane.showMessageDialog(dialog, "To use this stone, go to an already attempted door",
                        "Information Pane", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            }
            else if (stone.getStoneName().equals("Space Stone")) {
                if(theGP.getCC().getMyPopUp().getMyDialog().isVisible()) {
                    DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer("Would you like to use this item?", theGP);
                    if (yesNoDialog.getMyUserAnswer()) {
                        // thePlayer.useStone(stone);
                        // Flipping the question when this stone is used.
                    }
                }
                else {
                    // Show a colorful JOptionPane with a message and OK button
                    JOptionPane.showMessageDialog(dialog, "You can only use this Stone when answering trivia",
                            "Information Pane", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                }
            }

        }
        // Reset the UIManager properties
        UIManager.put("OptionPane.background", null);
        UIManager.put("Panel.background", null);
        UIManager.put("OptionPane.messageForeground", null);
        UIManager.put("Button.background", null);
        UIManager.put("Button.foreground", null);

        theGP.requestFocusInWindow();
    }
}