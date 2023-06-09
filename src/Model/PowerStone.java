package Model;



import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Power stone inherits from Stone class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class PowerStone extends Stone {
    /**
     *  Constructor initializes fields and sets image.
     */
    public PowerStone() {
        super("Power Stone", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/powerStone.png")));
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
        thePlayer.setPlayerSpeed(5);
        decreaseUses();
    }
    /**
     * Description of ability.
     * @return String description
     */
     public String getDescription() {
        String s = "Increase speed by 2! Can only be used once.";
        return s;
    }
}
