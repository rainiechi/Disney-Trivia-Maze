package Model;

import SQLite.DBRetriever;

public class Door {
    private DBRetriever myRetriever;
    private String myQuestion;
    private String myQAnswer;
    private String myOption1;
    private String myOption2;
    private String myOption3;
    private String myOption4;


    /** Boolean checks if door is unlocked*/
    private boolean myUnlocked;
    /** Boolean checks if door has been attempted*/
    private boolean myAttempted;
    private String myPlayerAnswer;


    //For testing, delete later
    public static void main(String[] args) {
        Door door = new Door();
        door.displayQuestion();
        System.out.println(door.myQAnswer);
        door.myRetriever.resetToUnused(door.myQuestion);
    }

    public Door () {
        myRetriever = new DBRetriever();
        myQuestion = myRetriever.getMyQuestion();
        myQAnswer = myRetriever.getMyAnswer();
        myOption1 = myRetriever.getMyOption1();
        myOption2 = myRetriever.getMyOption2();
        myOption3 = myRetriever.getMyOption3();
        myOption4 = myRetriever.getMyOption4();
        myUnlocked = false;
        myAttempted = false;
        myPlayerAnswer = null;
    }
    public void displayQuestion() {
        // Testing purposes. Will be done in GUI implementation once we have SQLite database.
        System.out.println(myQuestion);
    }
    public void lockedDoor(Player thePlayer) {
        if (thePlayer.hasSoulStone() && myAttempted) {
            System.out.println("Would you like to use the Soul Stone to attempt this door again?");
            // In GUI, if player chooses yes button, it will call the useAbility() method for Soul Stone.
        } else if (!thePlayer.hasSoulStone() && myAttempted) {
            System.out.println("Door has been attempted.");
        }
    }
    public void checkPlayerAnswer() {
        if (myQAnswer.toLowerCase().equals(myPlayerAnswer.toLowerCase())) {
            myUnlocked = true;
        }
        myAttempted = true;
    }
    public String getQAnswer() {
        return myQAnswer;
    }
    public boolean getMyUnlock() {
        return myUnlocked;
    }
    public void setMyUnlock(boolean lock) {
        myUnlocked= lock;
    }
    public boolean getAttempt() {
        return myAttempted;
    }
    public void setAttempted(boolean attempt) {
        myAttempted = attempt;
    }
    public String getPlayerMyAnswer() {
        return myPlayerAnswer;
    }
    public void setPlayerMyAnswer(String answer) {
        myPlayerAnswer = answer;
    }
}
