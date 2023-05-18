package Model;

public class RealityStone extends Stone{
    public RealityStone() {
        super("Reality Stone", "Revealed answer to trivia question!", 1);
    }
    public void useAbility(Door theDoor) {
        theDoor.getQuestionObject().getMyAnswer();
        decreaseUses();
    }
    public String getDescription() {
        String s = "Reveals the answer in a trivia. Can only be used once.";
        return s;
    }
}

