package View;

import java.awt.*;
import java.io.IOException;

/**
 * Represents a pop-up dialog for opening a chest.
 */
public class PopUpOpenChest {

    /**
     * Constructs a new PopUpOpenChest object and displays the dialog.
     *
     * @param parentFrame the parent frame for the dialog
     * @throws IOException if an IO error occurs
     */
    public PopUpOpenChest(Frame parentFrame) throws IOException {
        Frame frame = null;
        String message = "Do you want to open the chest?";
        Font fontForText = new Font("Berlin Sans FB", Font.PLAIN, 26);
        Color brownColor = new Color(123, 63, 0);

        new DialogForYesNoAnswer(parentFrame, message, fontForText, brownColor, Color.WHITE);
    }

    public static void main(String[] args) throws IOException {
        Frame frame = null;
        new PopUpOpenChest(frame);
    }
}
