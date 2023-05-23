package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class PowerStone extends Stone {
    public PowerStone() {
        super("Power Stone", "Speed has increased by +2!", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/powerStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void useAbility(Player thePlayer) {
        thePlayer.setPlayerSpeed(5);
        decreaseUses();
    }
    public String getDescription() {
        String s = "Increase speed by 2! Can only be used once.";
        return s;
    }
}
