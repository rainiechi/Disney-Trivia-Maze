package Model;



import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Space stone inherits from Stone class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class SpaceStone extends Stone{
    /**
     *  Constructor initializes fields and sets image.
     */
    public SpaceStone() {
        super("Space Stone",  1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/spaceStone.png")));
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
        decreaseUses();
    }

    /**
     * Description of ability.
     * @return String description
     */
    public String getDescription() {
        String s = "Allows you to bypass a door without going through trivia. Can only be used once.";
        return s;
    }
}


