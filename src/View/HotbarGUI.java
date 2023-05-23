package View;

import Model.Backpack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class HotbarGUI extends JDialog {
    private static final int HOTBAR_SIZE = 6;

    private JButton[] slots;
    private int selectedSlotIndex;

    Backpack mybackPack;

    public HotbarGUI(  Backpack theBackPack ) {
        slots = new JButton[HOTBAR_SIZE];
        selectedSlotIndex = 0;
        this.mybackPack = theBackPack;

        initializeGUI();
    }

    private void initializeGUI() {
        setTitle("Hotbar");

        setLayout(new FlowLayout(FlowLayout.LEADING, 0, 0)); // Set hgap and vgap to 0

        for (int i = 0; i < HOTBAR_SIZE; i++) {
            JButton slotButton = new JButton();
            slotButton.setPreferredSize(new Dimension(80, 80));
            slotButton.setBackground(new Color(234,210,182));


            slotButton.setLayout(new GridBagLayout());
            slotButton.setIcon( (Icon) mybackPack.getStone(i).getImage());

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

        pack();
        setVisible(true);

        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED) {
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
                        case KeyEvent.VK_7:
                            selectSlot(6);
                            break;
                        case KeyEvent.VK_8:
                            selectSlot(7);
                            break;
                        case KeyEvent.VK_9:
                            selectSlot(8);
                            break;
                        case KeyEvent.VK_LEFT:
                            scrollLeft();
                            break;
                        case KeyEvent.VK_RIGHT:
                            scrollRight();
                            break;
                    }
                }
                return false;
            }
        });
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
    public void useTheStone(){

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new HotbarGUI(new Backpack());
            }
        });
    }
}
