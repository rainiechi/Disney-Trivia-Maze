package View;

import Model.Backpack;
import Model.MindStone;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HotbarGUI extends JPanel {    private static final int BORDER = 15;
    private static final Color DARK_PINK = new Color(162, 72, 87);
    private static final Color RED = new Color(139, 0, 0);
    private static final Color BABY_PINK = new Color(245,218,223);
    private static final Color BABY_GREEN = new Color(230,255,239);
    private static final Color GREEN = new Color(95, 133, 117);
    private static final Color DARK_GREEN = new Color(53, 94, 59);

    private static final Color LIGHT_BLUE = new Color(230, 241, 255);
    private static final int HOTBAR_SIZE = 6;

    private JButton[] slots;
    private int selectedSlotIndex;

    //private Backpack myBackPack;
    private Player myPlayer;
    private GamePanel myGamePanel;
    private JDialog myDialog;

    public HotbarGUI(final Player thePlayer, GamePanel theGamePanel) {
        slots = new JButton[HOTBAR_SIZE];
        selectedSlotIndex = 0;
        myPlayer = thePlayer;
        myGamePanel = theGamePanel;
        setLayout(new FlowLayout()); // Set layout to FlowLayout
    }

    public JPanel updateGUI() {
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
                            initializeUI(useStone());
//                            try {
//                                //selectSlot(index);
//
//                            } catch (IOException ex) {
//                                throw new RuntimeException(ex);
//                            }
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


        }
        return this;
    }

//    private void askToUseStone() throws IOException {
//        Frame frame = null;
//        String message = "Do you want to use the Stone?";
//        Font fontForText = new Font("Berlin Sans FB", Font.PLAIN, 26);
//        Color brownColor = new Color(123, 63, 0);
//
//        DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer(frame, message, fontForText, brownColor, Color.WHITE);
//
//        if (myPlayer.getBackpack().getStone(selectedSlotIndex) != null &&
//                yesNoDialog.getPlayerAnswer()) {
//            System.out.println("I am using the " + (selectedSlotIndex + 1));
//            //myPlayer.getBackpack().getStone(selectedSlotIndex).useAbility();
//            myPlayer.useStone(myPlayer.getBackpack().getStone(selectedSlotIndex));
//        }
//    }
    public void initializeUI(final JPanel thePanel) {
        myDialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(myGamePanel), "Dialog", true);
        myDialog.getContentPane().add(thePanel);
        myDialog.setUndecorated(true);
        myDialog.pack();
        myDialog.setLocationRelativeTo(myGamePanel);
        myDialog.setVisible(true);
    }

    public JPanel useStone() {
        JLabel label = new JLabel("Would you like to use this item?");
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
        panel.setBorder(BorderFactory.createEmptyBorder(20, BORDER, 20, BORDER));
        panel.setLayout(new GridLayout(3, 1, 10, 10));
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
            myPlayer.useStone(myPlayer.getBackpack().getStone(selectedSlotIndex));
        });
        return panel;
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

    public static void main(String[] args) {
        // Initialize the backpack and GUI
        Player player = new Player();
        player.addToBackpack(new MindStone());
        player.addToBackpack(new MindStone());
        player.addToBackpack(new MindStone());


        HotbarGUI toolbar = new HotbarGUI(player, new GamePanel());

        // Create a JFrame to hold the toolbar
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 100);

        // Add the toolbar to the frame
        frame.add(toolbar.updateGUI());
        frame.setVisible(true);
    }

}
