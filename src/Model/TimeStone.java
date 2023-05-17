package Model;

public class TimeStone extends Stone{
    public TimeStone() {
        super("Time Stone", "Timer has increased by 5!", 1);
    }


    public void useAbility(Player thePlayer) {
        thePlayer.setTimeLimit(20);
        decreaseUses();
    }
    public String getDescription() {
        String s = "Permanently increase trivia timer by 5 seconds! Can only be used once.";
        return s;
    }
}

