package Model;

public class MindStone extends Stone{
    public MindStone() {
        setStoneName("Mind Stone");
    }
    public String getDescription() {
        String s = "Eliminates one incorrect answer. Can be used 3 times.";
        return s;
    }
}
