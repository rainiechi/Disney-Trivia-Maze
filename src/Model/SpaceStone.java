package Model;

public class SpaceStone extends Stone{
    public SpaceStone() {
        myUses = 1;
        myStoneName = "Space Stone";
        myAbility = "You can now pass through one door without trivia!";
    }
    public void useAbility(Player thePlayer) {
        thePlayer.setSpaceStone(true);
    }
    public String getDescription() {
        String s = "Allows you to bypass a door without going through trivia. Can only be used once.";
        return s;
    }
}


