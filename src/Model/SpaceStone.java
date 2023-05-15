package Model;

public class SpaceStone extends Stone{
    public SpaceStone() {
        super("Space Stone", "You can now pass through one door without trivia!", 1);
    }
    public void useAbility(Player thePlayer) {
        thePlayer.setSpaceStone(true);
        decreaseUses();
    }
    public String getDescription() {
        String s = "Allows you to bypass a door without going through trivia. Can only be used once.";
        return s;
    }
}


