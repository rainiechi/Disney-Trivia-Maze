package Model;

public class SoulStone extends Stone{
    public SoulStone() {
        setStoneName("Soul Stone");
    }
    public String getDescription() {
        String s = "Resets a locked door. Can only be used once.";
        return s;
    }
}

