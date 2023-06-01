package Model;

import View.PopUp;

import javax.imageio.ImageIO;
import java.io.IOException;

public class SpaceStone extends Stone{
    public SpaceStone() {
        super("Space Stone", "You can now pass through one door without trivia!", 1);
        try {
            setImage(ImageIO.read(getClass().getResourceAsStream("/res/stones/spaceStone.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void useAbility(Player thePlayer) {
        thePlayer.setSpaceStone(true);
        decreaseUses();
    }

    @Override
    public void useAbility(PopUp thePop) {

    }

    public String getDescription() {
        String s = "Allows you to bypass a door without going through trivia. Can only be used once.";
        return s;
    }
}


