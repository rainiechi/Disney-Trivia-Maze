package View;

import java.awt.*;
import java.io.IOException;
public class PopUpAttemptDoor {


    public static void main(String[] args) throws IOException {

        new PopUpAttemptDoor();
    }
    PopUpAttemptDoor() throws IOException {
        Frame frame = null;
        String door = "Disney-Trivia-Maze\\src\\res\\Miscellaneous_images\\boo door.png";

        DialogForYesNoAnswer dialog = new DialogForYesNoAnswer(null,door);

    }
}