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
        super("Power Stone", "Speed has increased by +2!", 1);
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
    public void useAbility(Player thePlayer) {
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
