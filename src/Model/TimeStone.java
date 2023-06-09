package Model;

import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Time stone inherits from Stone class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class TimeStone extends Stone{
    public TimeStone() {
        super("Time Stone", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/timeStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to call stone ability.
     * @param thePlayer player object
     */
    public void useAbility(final Player thePlayer) {
        if (thePlayer == null) {
            throw new IllegalArgumentException("Player cannot be null");
        }
        thePlayer.setTimeLimit(20);
        decreaseUses();
    }
    /**
     * Description of ability.
     * @return String description
     */
    public String getDescription() {
        String s = "Permanently increase trivia timer by 5 seconds! Can only be used once.";
        return s;
    }
}

