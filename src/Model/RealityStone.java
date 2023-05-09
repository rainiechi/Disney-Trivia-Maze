package Model;

public class RealityStone extends Stone{
    public RealityStone() {
        myStoneName = "Reality Stone";
        myAbility = "Revealed answer to trivia question!";
        myUses = 1;
    }
//    public void useAbility(Door theDoor) {
//        theDoor.getAnswer();
//        myUses--;
//    }
    public String getDescription() {
        String s = "Reveals the answer in a trivia. Can only be used once.";
        return s;
    }
}

