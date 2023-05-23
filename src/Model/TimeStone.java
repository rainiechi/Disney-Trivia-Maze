package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class TimeStone extends Stone{
    public TimeStone() {
        super("Time Stone", "Timer has increased by 5!", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/timeStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void useAbility(Player thePlayer) {
        thePlayer.setTimeLimit(20);
        decreaseUses();
    }
    public String getDescription() {
        String s = "Permanently increase trivia timer by 5 seconds! Can only be used once.";
        return s;
    }
}

