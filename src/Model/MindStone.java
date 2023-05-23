package Model;

public class MindStone extends Stone{
    public MindStone() {
        super("Mind Stone", "Eliminated 1 incorrect options.", 5);
    }
    public void useAbility(Player thePlayer) {
        // Eliminates 1 incorrect option. Can be used 5 times?
        // Not sure how to code this yet
        decreaseUses();
    }
    public String getDescription() {
        String s = "Eliminates one incorrect answer. Can be used 3 times.";
        return s;
    }
}
