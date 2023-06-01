package Model;

import View.PopUp;

import javax.imageio.ImageIO;
import java.io.IOException;

public class RealityStone extends Stone{
    public RealityStone() {
        super("Reality Stone", "Revealed answer to trivia question!", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/realityStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useAbility(Player thePlayer) {
        decreaseUses();
    }

    public String getDescription() {
        String s = "Reveals the answer in a trivia. Can only be used once.";
        return s;
    }
}

