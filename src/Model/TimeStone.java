package Model;

public class TimeStone extends Stone{
    public TimeStone() {
        setStoneName("Time Stone");
    }
    public String getDescription() {
        String s = "Permanently increase trivia timer by 5 seconds! Can only be used once.";
        return s;
    }
}

