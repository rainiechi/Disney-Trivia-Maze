package Model;


import javax.imageio.ImageIO;
import java.io.IOException;

public class SoulStone extends Stone{
    public SoulStone() {
        super("Soul Stone", "Walk up to the door you would like to attempt again.", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/soulStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void useAbility(Player thePlayer) {
        // Can only be used on a locked door. After failing a trivia, screen will pop up and say "Would you like to
        // use your Soul Stone to attempt this door again?"
        // Player can use stone and walk up to door to reset again.
        // Resets a locked door?
        thePlayer.setSoulStone(true);
        decreaseUses();
    }

    public String getDescription() {
        String s = "Resets a locked door. Can only be used once.";
        return s;
    }
}

