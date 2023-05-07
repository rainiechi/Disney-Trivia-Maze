package Model;

public class RealityStone extends Stone{
    public RealityStone() {
        setStoneName("Reality Stone");
    }
    public void useAbility() {
        // Reveals answer to door.
        // Not sure how to code this yet
    }
    public String getDescription() {
        String s = "Reveals the answer in a trivia. Can only be used once.";
        return s;
    }
}

