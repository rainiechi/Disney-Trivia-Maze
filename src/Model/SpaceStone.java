package Model;

public class SpaceStone extends Stone{
    public SpaceStone() {
        setStoneName("Space Stone");
    }
    public void useAbility() {
        player.setSpaceStone(true);
        setMyAbility("You can now pass through one door without trivia!");
    }
    public String getDescription() {
        String s = "Allows you to bypass a door without going through trivia. Can only be used once.";
        return s;
    }
}


