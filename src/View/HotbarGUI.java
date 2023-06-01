package View;

import Model.Backpack;
import Model.MindStone;
import Model.Player;
import Model.Stone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HotbarGUI extends JPanel {    private static final int BORDER = 15;
    private static final int HOTBAR_SIZE = 6;

    private JButton[] slots;
    private int selectedSlotIndex;

    //private Backpack myBackPack;
    private Player myPlayer;
    private GamePanel myGamePanel;


    public HotbarGUI(final Player thePlayer, GamePanel theGamePanel) {
        slots = new JButton[HOTBAR_SIZE];
        selectedSlotIndex = 0;
        myPlayer = thePlayer;
        myGamePanel = theGamePanel;
        setLayout(new FlowLayout()); // Set layout to FlowLayout
    }

    public void updateGUI() {
        removeAll();


        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for (int i = 0; i < HOTBAR_SIZE; i++) {
            JButton slotButton = new JButton();
            slotButton.setPreferredSize(new Dimension(50, 50));
            slotButton.setBackground(new Color(234, 210, 182));

            slotButton.setLayout(new GridBagLayout());
            if (myPlayer.getBackpack().getStone(i) != null) {
                slotButton.setIcon(new ImageIcon(myPlayer.getBackpack().getStone(i).getImage()));
                slotButton.setToolTipText(myPlayer.getBackpack().getStone(i).getDescription()); // Set the tooltip text
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
                            selectSlot(index);
                            try {
                                askToUseStone();
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }
                        } else {
                            clickedOnce = true;
                            Timer timer = new Timer(DOUBLE_CLICK_DELAY, new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent evt) {
                                    if (clickedOnce) {
                                        selectSlot(index);
                                    }
                                    clickedOnce = false;
                                }
                            });
                            timer.setRepeats(false);
                            timer.start();
                        }
                    }
                }
            });

            revalidate();
            repaint();
        }
    }
    private void askToUseStone() throws IOException {
        Stone stone = myPlayer.getBackpack().getStone(selectedSlotIndex);
        if (stone != null) {

            DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer("Would you like to use this item?", myGamePanel);
            if (yesNoDialog.getMyUserAnswer()) {

                if (stone.getStoneName().equals("Reality Stone")) {
                    //stone.useAbility(myGamePanel.getCC().getPop());
                } else if (stone.getStoneName().equals("Mind Stone")) {
                    //stone.useAbility(myGamePanel.getCC().getPop());
                } else {
                    myPlayer.useStone(myPlayer.getBackpack().getStone(selectedSlotIndex));
                }

            }
        }
    }

    public void selectSlot(int slot) {
        if (slot >= 0 && slot < HOTBAR_SIZE) {
            slots[selectedSlotIndex].setEnabled(true);
            selectedSlotIndex = slot;
            slots[selectedSlotIndex].setEnabled(false);
            myGamePanel.requestFocusInWindow();  //

        }
    }

    public void scrollLeft() {
        selectSlot((selectedSlotIndex - 1 + HOTBAR_SIZE) % HOTBAR_SIZE);
    }

    public void scrollRight() {
        selectSlot((selectedSlotIndex + 1) % HOTBAR_SIZE);
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
