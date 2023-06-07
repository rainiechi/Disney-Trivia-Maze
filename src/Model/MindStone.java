package Model;


import javax.imageio.ImageIO;
import java.io.IOException;
/**
 * Mind stone inherits from Stone class.
 *
 * @author Amanda Nguyen, Rainie Chi, Karan Sangha
 * @version 6/5/23
 */
public class MindStone extends Stone{
    /**
     *  Constructor initializes fields and sets image.
     */
    public MindStone() {
        super("Mind Stone", "Eliminated 1 incorrect option.", 5);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/mindStone.png")));
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
        String s = "Eliminates one incorrect answer. Can be used 5 times.";
        return s;
    }
}
