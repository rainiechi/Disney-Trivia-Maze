package Model;


import javax.imageio.ImageIO;
import java.io.IOException;

public class MindStone extends Stone{
    public MindStone() {
        super("Mind Stone", "Eliminated 1 incorrect options.", 5);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/mindStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useAbility(Player thePlayer) {
        decreaseUses();
    }

    public String getDescription() {
        String s = "Eliminates one incorrect answer. Can be used 5 times.";
        return s;
    }
}
