package Model;

public class Stone {
    private String myStoneName;
    private String myAbility;
    Player player;
    public Stone() {
        player = new Player();
    }

    public void useAbility() {
        switch (myStoneName) {
            case "Power Stone":
                // Permamently increases player speed by +2
                player.setPlayerSpeed(5);
                // will be used later on
                myAbility = "Speed has increased by +2!";
                break;
            case "Time Stone":
                // Permanently sets timer of questions to 20 seconds
                // door.setTimer(20);
                myAbility = "Timer has increased by 5!";
                break;
            case "Mind Stone":
                // Eliminates 1 incorrect option. Can be used 5 times?
                // Not sure how to code this yet

                break;
            case "Reality Stone":
                // Reveals answer to door.
                // Not sure how to code this yet
                break;
            case "Soul Stone":
                // Can only be used on a locked door. After failing a trivia, screen will pop up and say "Would you like to
                // use your Soul Stone to attempt this door again?"
                // Player can use stone and walk up to door to reset again.
                // Resets a locked door?
                player.setSoulStone(true);
                myAbility = "Walk up to the door you would like to attempt again.";
                break;
            case "Space Stone":
                player.setSpaceStone(true);
                myAbility = "You can now pass through one door without trivia!";
                break;
        }
    }

    public String getStoneName() {
        return myStoneName;
    }

    public void setStoneName(String name) {
        myStoneName = name;
    }

    public String getmyAbility() {
        return myAbility;
    }

    // used for testing
    public String toString() {
        return "Stone:" + myStoneName;
    }
}
