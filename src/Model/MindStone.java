package Model;

public class MindStone extends Stone{
    public MindStone() {
        setStoneName("Mind Stone");
    }
    public void useAbility() {
        // Eliminates 1 incorrect option. Can be used 5 times?
        // Not sure how to code this yet

    }
    public String getDescription() {
        String s = "Eliminates one incorrect answer. Can be used 3 times.";
        return s;
    }
}
