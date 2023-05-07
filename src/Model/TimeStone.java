package Model;

public class TimeStone extends Stone{
    public TimeStone() {
        setStoneName("Time Stone");
    }

    public void useAbility() {
        // Permanently sets timer of questions to 20 seconds
        // door.setTimer(20);
        setMyAbility("Timer has increased by 5!");
    }
    public String getDescription() {
        String s = "Permanently increase trivia timer by 5 seconds! Can only be used once.";
        return s;
    }
}

