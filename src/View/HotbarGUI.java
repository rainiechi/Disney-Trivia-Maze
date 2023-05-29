package View;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HotbarGUI extends JPanel implements KeyListener {
    private static final int HOTBAR_SIZE = 6;

    private JButton[] slots;
    private int selectedSlotIndex;

    //private Backpack myBackPack;
    private Player myPlayer;

    public HotbarGUI(final Player thePlayer) {
        slots = new JButton[HOTBAR_SIZE];
        selectedSlotIndex = 0;
        myPlayer = thePlayer;
        setLayout(new FlowLayout()); // Set layout to FlowLayout
        updateGUI();
    }

    public void updateGUI() {
        removeAll(); // Clear existing buttons
        setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));

        for (int i = 0; i < HOTBAR_SIZE; i++) {
            JButton slotButton = new JButton();
            slotButton.setPreferredSize(new Dimension(50, 50));
            slotButton.setBackground(new Color(234, 210, 182));
            //slotButton.addKeyListener(this);

            slotButton.setLayout(new GridBagLayout());
            if (myPlayer.getBackpack().getStone(i) != null) {
                slotButton.setIcon(new ImageIcon(myPlayer.getBackpack().getStone(i).getImage()));
                slotButton.setToolTipText(myPlayer.getBackpack().getStone(i).getDescription()); // Set the tooltip text
            }

            slots[i] = slotButton;
            add(slotButton);

            final int index = i;
            slotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectSlot(index);
                }
            });
        }
    }

    private void askToUseStone() throws IOException {
        Frame frame = null;
        String message = "Do you want to use the Stone?";
        Font fontForText = new Font("Berlin Sans FB", Font.PLAIN, 26);
        Color brownColor = new Color(123, 63, 0);

        DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer(frame, message, fontForText, brownColor, Color.WHITE);

        if (myPlayer.getBackpack().getStone(selectedSlotIndex) != null &&
                yesNoDialog.getPlayerAnswer()) {
            System.out.println("I am using the " + (selectedSlotIndex + 1));
        }
    }

    public void selectSlot(int slot) {
        if (slot >= 0 && slot < HOTBAR_SIZE) {
            slots[selectedSlotIndex].setEnabled(true);
            selectedSlotIndex = slot;
            slots[selectedSlotIndex].setEnabled(false);
            System.out.println("Hello "+slot);
        }
    }

    public void scrollLeft() {
        selectSlot((selectedSlotIndex - 1 + HOTBAR_SIZE) % HOTBAR_SIZE);
    }

    public void scrollRight() {
        selectSlot((selectedSlotIndex + 1) % HOTBAR_SIZE);
    }


    @Override
    public void keyTyped(KeyEvent e) {
        // Implementation for keyTyped event (if needed)
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_1:
                selectSlot(0);
                break;
            case KeyEvent.VK_2:
                selectSlot(1);
                break;
            case KeyEvent.VK_3:
                selectSlot(2);
                break;
            case KeyEvent.VK_4:
                selectSlot(3);
                break;
            case KeyEvent.VK_5:
                selectSlot(4);
                break;
            case KeyEvent.VK_6:
                selectSlot(5);
                break;
            case KeyEvent.VK_LEFT:
                scrollLeft();
                break;
            case KeyEvent.VK_RIGHT:
                scrollRight();
                break;
            case KeyEvent.VK_ENTER:
                try {
                    askToUseStone();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Implementation for keyReleased event (if needed)
    }



//    public static void main(String[] args) {
//        // Initialize the backpack and GUI
//        Backpack pack = new Backpack();
//        pack.addToBackpack(new MindStone());
//        pack.addToBackpack(new MindStone());
//        pack.addToBackpack(new MindStone());
//        pack.addToBackpack(new MindStone());
//        pack.addToBackpack(new MindStone());
//        pack.addToBackpack(new MindStone());
//
//        HotbarGUI toolbar = new HotbarGUI(pack);
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
