package Model;

public class RealityStone extends Stone{
    public RealityStone() {
        setStoneName("Reality Stone");
    }
    public String getDescription() {
        String s = "Reveals the answer in a trivia. Can only be used once.";
        return s;
    }
}

