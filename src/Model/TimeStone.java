package Model;

public class TimeStone extends Stone{
    public TimeStone() {
        myStoneName = "Time Stone";
        myAbility = "Timer has increased by 5!";
        myUses = 1;
    }

    public void useAbility() {
        // Permanently sets timer of questions to 20 seconds
        // door.setTimer(20);
        myUses--;

    }
    public String getDescription() {
        String s = "Permanently increase trivia timer by 5 seconds! Can only be used once.";
        return s;
    }
}

