package View;

import java.awt.*;
import java.io.IOException;

/**
 * Represents a pop-up dialog for attempting a door again.
 */
public class PopUpAttemptDoor {

    /**
     * Constructs a new PopUpAttemptDoor object and displays the dialog.
     *
     * @param theParent the parent frame for the dialog
     * @throws IOException if an IO error occurs
     */
    public PopUpAttemptDoor(Frame theParent) throws IOException {
        Frame frame = null;
        String door = "<html>Do you want to Attempt" +
                "<br/>" +
                "the Door Again?<html>";
        Font fontForText = new Font("Berlin Sans FB", Font.PLAIN, 32);
        Color brownColor = new Color(123, 63, 0);

        new DialogForYesNoAnswer(theParent, door, fontForText, brownColor, Color.WHITE);
    }

    public static void main(String[] args) throws IOException {
        Frame frame = null;
        new PopUpAttemptDoor(frame);
    }
}
