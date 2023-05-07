package Model;

public class PowerStone extends Stone {
    public PowerStone() {
        setStoneName("Power Stone");
    }
    public void useAbility(Player thePlayer) {
        thePlayer.setPlayerSpeed(5);
        // will be used later on
        setMyAbility("Speed has increased by +2!");
    }
    public String getDescription() {
        String s = "Increase speed by 2! Can only be used once.";
        return s;
    }
}
