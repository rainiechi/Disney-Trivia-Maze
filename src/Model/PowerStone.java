package Model;

public class PowerStone extends Stone {
    public PowerStone() {
        setStoneName("Power Stone");
    }
    public String getDescription() {
        String s = "Increase speed by 2! Can only be used once.";
        return s;
    }
}
