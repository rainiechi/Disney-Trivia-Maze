package View;

import Model.Backpack;
import Model.MindStone;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class HotbarGUI implements KeyListener {
    private static final int HOTBAR_SIZE = 6;

    private JButton[] slots;
    private int selectedSlotIndex;

    Backpack mybackPack;
    JDialog myHotbar;

    public HotbarGUI(final Backpack theBackPack) {
        slots = new JButton[HOTBAR_SIZE];
        selectedSlotIndex = 0;
        this.mybackPack = theBackPack;
        myHotbar = new JDialog(); // Initialize to null initially
    }

    private void initializeGUI() {

        myHotbar.getContentPane().removeAll();

        myHotbar.setTitle("Hotbar");
        myHotbar.setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0)); // Set hgap and vgap to 0
        myHotbar.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE); // Set the close operation

        for (int i = 0; i < HOTBAR_SIZE; i++) {
            JButton slotButton = new JButton();
            slotButton.setPreferredSize(new Dimension(50, 50));
            slotButton.setBackground(new Color(234, 210, 182));
            slotButton.addKeyListener(this);

            slotButton.setLayout(new GridBagLayout());
            if (mybackPack.getStone(i) != null) {
                slotButton.setIcon(new ImageIcon(mybackPack.getStone(i).getImage()));
                slotButton.setToolTipText(mybackPack.getStone(i).getDescription()); // Set the tooltip text
            }

            slots[i] = slotButton;
            myHotbar.add(slotButton);

            final int index = i;
            slotButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selectSlot(index);
                }
            });
        }

        myHotbar.pack();
        myHotbar.setVisible(true);
    }

    private void askToUseStone() throws IOException {
        Frame frame = null;
        String message = "Do you want to use the Stone?";
        Font fontForText = new Font("Berlin Sans FB", Font.PLAIN, 26);
        Color brownColor = new Color(123, 63, 0);

        DialogForYesNoAnswer yesNoDialog = new DialogForYesNoAnswer(frame, message, fontForText, brownColor, Color.WHITE);

        if (mybackPack.getStone(selectedSlotIndex) != null &&
                yesNoDialog.getPlayerAnswer()) {
            System.out.println("I am using the " + (selectedSlotIndex + 1));
        }
        System.out.println( yesNoDialog.getPlayerAnswer() );
    }

    public void selectSlot(int slot) {
        if (slot >= 0 && slot < HOTBAR_SIZE) {
            slots[selectedSlotIndex].setEnabled(true);
            selectedSlotIndex = slot;
            slots[selectedSlotIndex].setEnabled(false);
        }
    }

    public void scrollLeft() {
        selectSlot((selectedSlotIndex - 1 + HOTBAR_SIZE) % HOTBAR_SIZE);
    }

    public void scrollRight() {
        selectSlot((selectedSlotIndex + 1) % HOTBAR_SIZE);
    }

    private void updateGUI() {
        initializeGUI();
    }

    public static void main(String[] args) {

        Backpack pack = new Backpack();
        pack.addToBackpack(new MindStone());
        pack.addToBackpack(new MindStone());
        pack.addToBackpack(new MindStone());
        pack.addToBackpack(new MindStone());
        pack.addToBackpack(new MindStone());
        pack.addToBackpack(new MindStone());

        HotbarGUI gui = new HotbarGUI(pack);

        gui.updateGUI();

        pack.deleteStone(0);

        gui.updateGUI();
    }

    @Override
    public void keyTyped(KeyEvent e) {

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
                break;}
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
