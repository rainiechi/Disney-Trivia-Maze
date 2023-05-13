package Model;

public class PowerStone extends Stone {
    public PowerStone() {
        myStoneName = "Power Stone";
        myAbility = "Speed has increased by +2!";
        myUses = 1;
    }
    public void useAbility(Player thePlayer) {
        thePlayer.setPlayerSpeed(5);
    }
    public String getDescription() {
        String s = "Increase speed by 2! Can only be used once.";
        return s;
    }
}
