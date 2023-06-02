package View;

import Model.Backpack;
import Model.MindStone;
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
        if (stone != null) {

            DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer("Would you like to use this item?", theGP);
            if (yesNoDialog.getMyUserAnswer()) {

                if (stone.getStoneName().equals("Reality Stone")) {
                    if (theGP.getCC().getPop().getMyDialog().isVisible()) {
                        theGP.getCC().getPop().disableWrongAnswerButton(3);
                        thePlayer.useStone(stone);
                    }
                }
                else if (stone.getStoneName().equals("Mind Stone")) {
                    if (theGP.getCC().getPop().getMyDialog().isVisible()) {
                        theGP.getCC().getPop().disableWrongAnswerButton(1);
                        thePlayer.useStone(stone);
                    }
                }
                else {
                    thePlayer.useStone(stone);
                }
            }
            theGP.requestFocusInWindow();
        }
    }
//    public static void main(String[] args) {
//        // Initialize the backpack and GUI
//        Player player = new Player();
//        player.addToBackpack(new MindStone());
//        player.addToBackpack(new MindStone());
//        player.addToBackpack(new MindStone());
//
//
//        HotbarGUI toolbar = new HotbarGUI(player, new GamePanel());
//
//        // Create a JFrame to hold the toolbar
//        JFrame frame = new JFrame();
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(400, 100);
//
//        // Add the toolbar to the frame
//        frame.add(toolbar.updateGUI());
//        frame.setVisible(true);
//    }

}