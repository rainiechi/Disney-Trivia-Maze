package Model;



import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Reality stone inherits from Stone class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class RealityStone extends Stone{
    /**
     *  Constructor initializes fields and sets image.
     */
    public RealityStone() {
        super("Reality Stone", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/realityStone.png")));
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
        String s = "Reveals the answer in a trivia. Can only be used once.";
        return s;
    }
}

