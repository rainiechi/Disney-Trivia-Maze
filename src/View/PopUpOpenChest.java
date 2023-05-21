package View;

import java.awt.*;
import java.io.IOException;
public class PopUpOpenChest {


    public static void main(String[] args) throws IOException {

        new PopUpOpenChest();
    }
    PopUpOpenChest() throws IOException {
        Frame frame = null;
        String chest = "Disney-Trivia-Maze\\src\\res\\Miscellaneous_images\\open chest.png";

        DialogForYesNoAnswer dialog = new DialogForYesNoAnswer(null,chest);
      //  "Disney-Trivia-Maze\\src\\res\\Miscellaneous_images\\boo door.png"
    }
}