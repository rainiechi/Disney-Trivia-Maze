package Model;

public class MindStone extends Stone{
    public MindStone() {
        myUses = 5;
        myStoneName = "Mind Stone";
        myAbility = "Eliminated 1 incorrect options.";
    }
    public void useAbility() {
        // Eliminates 1 incorrect option. Can be used 5 times?
        // Not sure how to code this yet
        myUses--;
    }
    public String getDescription() {
        String s = "Eliminates one incorrect answer. Can be used 3 times.";
        return s;
    }
}
