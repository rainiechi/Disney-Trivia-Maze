package Model;


import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Soul stone inherits from Stone class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class SoulStone extends Stone{
    /**
     *  Constructor initializes fields and sets image.
     */
    public SoulStone() {
        super("Soul Stone", "Walk up to the door you would like to attempt again.", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/soulStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Method to call stone ability.
     * @param thePlayer player object
     */
    public void useAbility(Player thePlayer) {
        decreaseUses();
    }
    /**
     * Description of ability.
     * @return String description
     */
    public String getDescription() {
        String s = "Resets a locked door. Can only be used once.";
        return s;
    }
}

