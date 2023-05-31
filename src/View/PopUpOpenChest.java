package View;

import Model.Chest;

import java.awt.*;
import java.io.IOException;

/**
 * Represents a pop-up dialog for opening a chest.
 */
public class PopUpOpenChest {

    /**
     * Constructs a new PopUpOpenChest object and displays the dialog.
     *
     * @param theChest It is the chest that contains the stone
     * @throws IOException if an IO error occurs
     */
    public PopUpOpenChest(Chest theChest) {
        String message = "Open the chest?";
        Font fontForText = new Font("Berlin Sans FB", Font.PLAIN, 16);
        //Color brownColor = new Color(123, 63, 0);

        Frame parentFrame = null;
        try {
            new DialogForYesNoAnswer(parentFrame, message, fontForText, Color.PINK, Color.WHITE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
